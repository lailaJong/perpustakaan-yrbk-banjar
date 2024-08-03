import React, { useState, useEffect } from 'react';
import { Container, Paper, Typography, Button, Box, Grid, Snackbar, Alert } from '@mui/material';
import { styled } from '@mui/material/styles';
import { useParams, useNavigate, useLocation } from 'react-router-dom';
import api from '../../utility/api';
import defaultAvatar from '../../assets/rabbit-mammal-animals.png';
import EditUserStatusDialog from './EditUserStatusDialog';

const UserImage = styled('img')({
    minWidth: '150px',
    width: '100%',
    minHeight: '150px',
    height: 'auto',
    objectFit: 'cover',
    borderRadius: '8px',
});

const DetailItem = styled(Typography)({
    marginBottom: '8px',
});

const UserDetail = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const { id: userId } = useParams();
    const [user, setUser] = useState({
        id: '',
        imageUrl: '',
        fullName: '',
        username: '',
        gender: '',
        birthPlace: '',
        birthDate: '',
        address: '',
        phoneNumber: '',
        point: '',
        additionalLoanQuota: '',
        additionalLoanDuration: '',
        status: '',
    });
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [isDialogOpen, setDialogOpen] = useState(false); // Dialog open state

    useEffect(() => {
        const fetchUserDetail = async () => {
            try {
                if (location.state?.showSnackbar) {
                    setSnackbarOpen(true);
                }
                const response = await api.get(`/api/users/${userId}`);
                setUser(response.data);
            } catch (error) {
                console.error('Error fetching user detail:', error);
            }
        };

        fetchUserDetail();
    }, [userId, location.state]);

    const handleEditStatus = () => {
        setDialogOpen(true);
    };

    const handleDialogClose = () => {
        setDialogOpen(false);
    };

    const handleSaveSuccess = () => {
        setSnackbarOpen(true);
        handleDialogClose();
    };

    const handleSnackbarClose = () => {
        setSnackbarOpen(false);
    };

    console.log(`User Detail : ${user}`);

    return (
        <Container maxWidth="lg">
            <Snackbar
                open={snackbarOpen}
                autoHideDuration={6000}
                onClose={handleSnackbarClose}
                anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
            >
                <Alert onClose={handleSnackbarClose} severity="success" sx={{ width: '100%' }}>
                    Status pemustaka berhasil diperbarui!
                </Alert>
            </Snackbar>
            <EditUserStatusDialog
                open={isDialogOpen}
                onClose={handleDialogClose}
                onSave={handleSaveSuccess}
                member={user}
            />
            <Box component={Paper} p={4} mt={4} boxShadow={3}>
                <Typography variant="h4" gutterBottom>
                    Detail Pemustaka
                </Typography>
                <Grid container spacing={3}>
                    <Grid item xs={12} sm={4}>
                        <UserImage src={user.imageUrl || defaultAvatar} alt={user.fullName} />
                    </Grid>
                    <Grid item xs={12} sm={8}>
                        <Typography variant="h4" color="primary" gutterBottom>
                            {user.fullName || 'Nama Pemustaka xxxxxxxxxxxx'}
                        </Typography>
                        <Grid container spacing={2}>
                            <Grid item xs={12} sm={6}>
                                <DetailItem variant="body1">Username: {user.username || 'xxxxxxxx'}</DetailItem>
                                <DetailItem variant="body1">Jenis Kelamin: {user.gender || 'xxxxxxxx'}</DetailItem>
                                <DetailItem variant="body1">Tempat Tanggal Lahir: {`${user.birthPlace}, ${user.birthDate}` || 'xxxxxxxx'}</DetailItem>
                                <DetailItem variant="body1">Alamat: {user.address || 'xxxxxxxxxxxx'}</DetailItem>
                            </Grid>
                            <Grid item xs={12} sm={6}>
                                <DetailItem variant="body1">No. Telp: {user.phoneNumber || 'xxxxxxxx'}</DetailItem>
                                <DetailItem variant="body1">Point: {user.point || 'xxxxxxxx'}</DetailItem>
                                <Box display='flex' flexDirection='row'>
                                    <DetailItem variant="body1">
                                        Reward:
                                    </DetailItem>
                                    <Box display='flex' flexDirection='column' marginLeft='6px'>
                                        <DetailItem variant="body1">
                                            Total Buku : {user.additionalLoanQuota || 'xxxxxxxx'}
                                        </DetailItem>
                                        <DetailItem variant="body1">
                                            Lama Pinjam : {user.additionalLoanDuration || 'xxxxxxxx'}
                                        </DetailItem>
                                    </Box>
                                </Box>
                                <DetailItem variant="body1">Status: {user.status || 'xxxxxxxx'}</DetailItem>
                            </Grid>
                        </Grid>
                    </Grid>
                </Grid>
                <Box mt={3} display="flex" justifyContent="space-between" width="100%">
                    <Button variant="contained" size="large" sx={{ mt: 2 }} onClick={() => navigate('/admin/users')}>
                        <Typography variant="button">Back</Typography>
                    </Button>
                    <Button variant="contained" color="tertiary" size="large" sx={{ mt: 2 }} onClick={() => handleEditStatus()}>
                        <Typography variant="button">
                            {user.status === 'Active' ? 'Deactive' : 'Activate'}
                        </Typography>
                    </Button>
                </Box>
            </Box>
        </Container>
    );
};

export default UserDetail;
