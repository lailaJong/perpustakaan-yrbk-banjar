import React, { useState, useEffect } from 'react';
import { Container, Grid, Paper, Typography, Button, TextField, InputAdornment, IconButton, Box, Snackbar, Alert } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import AddIcon from '@mui/icons-material/Add';
import { styled } from '@mui/material/styles';
import { useNavigate, useLocation } from 'react-router-dom';
import api from '../../utility/api';
import EditUserStatusDialog from './EditUserStatusDialog';

const UserImage = styled('img')({
    width: '100px',
    height: '150px',
    objectFit: 'cover',
});

const UserItem = styled(Paper)({
    display: 'flex',
    alignItems: 'center',
    padding: '16px',
    marginBottom: '16px',
});

const UserDetails = styled(Box)({
    flexGrow: 1,
    marginLeft: '16px',
});

const AddButton = styled(Button)({
    backgroundColor: '#FFA726',
    color: 'white',
    '&:hover': {
        backgroundColor: '#FB8C00',
    },
});

const UsersList = () => {
    const [users, setUsers] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [isDialogOpen, setDialogOpen] = useState(false);
    const [selectedUser, setSelectedUser] = useState({
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
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                if (location.state?.showSnackbar) {
                    setSnackbarOpen(true);
                }
                const response = await api.get('/api/users');
                setUsers(response.data);
            } catch (error) {
                console.error('Error fetching users:', error);
            }
        };

        fetchUsers();
    }, [location]);

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredUsers = users.filter((user) =>
        user.fullName.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const handleAdd = () => {
        navigate('/admin/users/add');
    };

    const handleEditStatus = (user) => {
        setDialogOpen(true);
        setSelectedUser(user);
    };

    const handleSnackbarClose = () => {
        setSnackbarOpen(false);
        setSelectedUser({
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
    };

    const handleDialogClose = () => {
        setDialogOpen(false);
    };

    const handleSaveSuccess = () => {
        setSnackbarOpen(true);
        handleDialogClose(false);
    };

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
                member={selectedUser}
            />
            <Typography variant="h4" gutterBottom>
                Daftar Pemustaka
            </Typography>
            <Box display="flex" justifyContent="space-between" alignItems="center" marginBottom="16px">
                <TextField
                    variant="outlined"
                    placeholder="cari buku berdasarkan judul"
                    fullWidth
                    margin="normal"
                    value={searchTerm}
                    onChange={handleSearch}
                    InputProps={{
                        endAdornment: (
                            <InputAdornment position="end">
                                <IconButton>
                                    <SearchIcon />
                                </IconButton>
                            </InputAdornment>
                        ),
                    }}
                />
                <Box marginLeft="16px">
                    <AddButton variant="contained" onClick={handleAdd} startIcon={<AddIcon />}>
                        Tambah Pemustaka
                    </AddButton>
                </Box>
            </Box>
            <Grid container spacing={3}>
                {filteredUsers.map((user, index) => (
                    <Grid item xs={12} key={index}>
                        <UserItem>
                            <UserImage src={user.imageUrl} alt={user.title} />
                            <UserDetails>
                                <Typography variant="h6">{user.fullName}</Typography>
                                <Typography variant="body1">Username: {user.username}</Typography>
                                <Typography variant="body1">Point: {user.point}</Typography>
                                <Typography variant="body1">Jenis Kelamin: {user.gender}</Typography>
                                <Typography variant="body1">Status: {user.status}</Typography>
                            </UserDetails>
                            <Box display="flex" flexDirection="column">
                                <Button variant="contained" color="primary" style={{ marginBottom: '8px' }} onClick={() => { navigate(`/admin/users/detail/${user.id}`, { state: { from: location } }) }}>
                                    Lihat Detail
                                </Button>
                                <Button variant="contained" color="tertiary" onClick={() => handleEditStatus(user)}>
                                    {user.status === 'Active' ? 'Deactive' : 'Activate'}
                                </Button>
                            </Box>
                        </UserItem>
                    </Grid>
                ))}
            </Grid>
        </Container>
    );
};

export default UsersList;