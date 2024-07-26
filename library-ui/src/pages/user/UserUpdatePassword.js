import React, { useState } from 'react';
import { Container, Grid, Paper, Typography, TextField, Button } from '@mui/material';
import { styled } from '@mui/system';
import { useNavigate } from 'react-router-dom';

const UpdatePasswordContainer = styled(Container)(({ theme }) => ({
    marginTop: theme.spacing(4),
}));

const UpdatePasswordPaper = styled(Paper)(({ theme }) => ({
    padding: theme.spacing(4),
    textAlign: 'center',
}));

const UpdatePasswordButton = styled(Button)(({ theme }) => ({
    margin: theme.spacing(1),
}));

const UserUpdatePassword = () => {
    const navigate = useNavigate();

    const [currentPassword, setCurrentPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        // Handle password update logic
    };

    return (
        <UpdatePasswordContainer maxWidth="sm">
            <Typography variant="h4" gutterBottom>
                Perbarui Kata Sandi
            </Typography>
            <UpdatePasswordPaper elevation={3}>
                <form onSubmit={handleSubmit}>
                    <Grid container spacing={2} direction="column">
                        <Grid item>
                            <TextField
                                label="Kata Sandi Saat Ini"
                                variant="outlined"
                                fullWidth
                                required
                                value={currentPassword}
                                onChange={(e) => setCurrentPassword(e.target.value)}
                            />
                        </Grid>
                        <Grid item>
                            <TextField
                                label="Kata Sandi Baru"
                                variant="outlined"
                                fullWidth
                                required
                                value={newPassword}
                                onChange={(e) => setNewPassword(e.target.value)}
                            />
                        </Grid>
                        <Grid item>
                            <TextField
                                label="Konfirmasi Kata Sandi"
                                variant="outlined"
                                fullWidth
                                required
                                value={confirmPassword}
                                onChange={(e) => setConfirmPassword(e.target.value)}
                            />
                        </Grid>
                        <Grid item>
                            <UpdatePasswordButton type="submit" variant="contained" color="primary">
                                Simpan
                            </UpdatePasswordButton>
                            <UpdatePasswordButton variant="contained" color="secondary" onClick={() => {navigate('/user/profile')}}>
                                Batal
                            </UpdatePasswordButton>
                        </Grid>
                    </Grid>
                </form>
            </UpdatePasswordPaper>
        </UpdatePasswordContainer>
    );
};

export default UserUpdatePassword;