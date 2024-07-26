// src/pages/UpdateProfil.js
import React, { useState, useEffect } from 'react';
import { Container, Grid, Paper, Typography, TextField, Button, Checkbox, FormControlLabel, MenuItem } from '@mui/material';
import { LocalizationProvider, DatePicker } from '@mui/x-date-pickers';
import id from 'date-fns/locale/id';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFnsV3';
import { styled } from '@mui/system';
import { useNavigate } from 'react-router-dom';

const UpdateProfileContainer = styled(Container)(({ theme }) => ({
    marginTop: theme.spacing(4),
}));

const UpdateProfilePaper = styled(Paper)(({ theme }) => ({
    padding: theme.spacing(4),
}));

const UpdateProfileButton = styled(Button)(({ theme }) => ({
    margin: theme.spacing(1),
}));

const UserUpdateProfile = () => {
    const navigate = useNavigate();
    
    const [profileData, setProfileData] = useState({
        fullName: '',
        username: '',
        phoneNumber: '',
        birthPlace: '',
        birthDate: null,
        gender: '',
        address: '',
        isConfirmed: false,
    });

    const [isFormValid, setIsFormValid] = useState(false);

    useEffect(() => {
        const { fullName, username, phoneNumber, birthPlace, birthDate, gender, address, isConfirmed } = profileData;
        const isValid =
            fullName &&
            username &&
            phoneNumber &&
            birthPlace &&
            birthDate &&
            gender &&
            address &&
            isConfirmed;
        setIsFormValid(isValid);
    }, [profileData]);

    const handleChange = (e) => {
        const { name, value, checked, type } = e.target;
        setProfileData({
            ...profileData,
            [name]: type === 'checkbox' ? checked : value,
        });
    };

    const handleDateChange = (date) => {
        setProfileData((prevData) => ({
            ...prevData,
            birthDate: date,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Handle profile update logic
    };

    return (
        <UpdateProfileContainer maxWidth="md">
            <Typography variant="h4" gutterBottom>
                Edit Profil
            </Typography>
            <UpdateProfilePaper elevation={3}>
                <form onSubmit={handleSubmit}>
                    <Grid container spacing={2}>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                label="Nama Lengkap"
                                name="fullName"
                                variant="outlined"
                                fullWidth
                                required
                                value={profileData.fullName}
                                onChange={handleChange}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                label="Tempat Lahir"
                                name="birthPlace"
                                variant="outlined"
                                fullWidth
                                required
                                value={profileData.birthPlace}
                                onChange={handleChange}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                label="Username"
                                name="username"
                                variant="outlined"
                                fullWidth
                                required
                                value={profileData.username}
                                onChange={handleChange}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <LocalizationProvider dateAdapter={AdapterDateFns} adapterLocale={id}>
                                <DatePicker
                                    label='Tanggal Lahir'
                                    value={profileData.birthDate}
                                    onChange={handleDateChange}
                                    sx={{ width: '100%' }}
                                    slotProps={{ textField: { variant: 'outlined', width: '100%' } }}
                                // renderInput={(params) => <TextField {...params} variant='outlined' fullWidth sx={{ width: '100%' }} />}
                                />
                            </LocalizationProvider>
                        </Grid>
                        {/* <Grid item xs={12} sm={6}>
                            <TextField
                                label="Tanggal Lahir"
                                name="birthDate"
                                variant="outlined"
                                select
                                fullWidth
                                required
                                value={profileData.birthDate}
                                onChange={handleChange}
                            >
                                {[...Array(31).keys()].map((day) => (
                                    <MenuItem key={day + 1} value={day + 1}>
                                        {day + 1}
                                    </MenuItem>
                                ))}
                            </TextField>
                            <TextField
                                name="birthMonth"
                                variant="outlined"
                                select
                                fullWidth
                                required
                                value={profileData.birthMonth}
                                onChange={handleChange}
                                style={{ marginTop: '8px' }}
                            >
                                {[...Array(12).keys()].map((month) => (
                                    <MenuItem key={month + 1} value={month + 1}>
                                        {month + 1}
                                    </MenuItem>
                                ))}
                            </TextField>
                            <TextField
                                name="birthYear"
                                variant="outlined"
                                select
                                fullWidth
                                required
                                value={profileData.birthYear}
                                onChange={handleChange}
                                style={{ marginTop: '8px' }}
                            >
                                {Array.from({ length: 100 }, (_, i) => new Date().getFullYear() - i).map((year) => (
                                    <MenuItem key={year} value={year}>
                                        {year}
                                    </MenuItem>
                                ))}
                            </TextField>
                        </Grid> */}
                        <Grid item xs={12} sm={6}>
                            <TextField
                                label="Nomor HP"
                                name="phoneNumber"
                                variant="outlined"
                                fullWidth
                                required
                                value={profileData.phoneNumber}
                                onChange={handleChange}
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                label="Jenis Kelamin"
                                name="gender"
                                variant="outlined"
                                select
                                fullWidth
                                required
                                value={profileData.gender}
                                onChange={handleChange}
                            >
                                <MenuItem value="Laki-laki">Laki-laki</MenuItem>
                                <MenuItem value="Perempuan">Perempuan</MenuItem>
                            </TextField>
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                label="Alamat Tinggal"
                                name="address"
                                variant="outlined"
                                fullWidth
                                required
                                value={profileData.address}
                                onChange={handleChange}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <FormControlLabel
                                control={
                                    <Checkbox
                                        name="isConfirmed"
                                        color="primary"
                                        checked={profileData.isConfirmed}
                                        onChange={handleChange}
                                    />
                                }
                                label="Saya menyatakan data yang diisi benar dan dapat dipertanggungjawabkan"
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <UpdateProfileButton type="submit" variant="contained" color="primary" disabled={!isFormValid}>
                                Simpan
                            </UpdateProfileButton>
                            <UpdateProfileButton variant="contained" color="secondary" onClick={() => {navigate('/user/profile')}}>
                                Batal
                            </UpdateProfileButton>
                        </Grid>
                    </Grid>
                </form>
            </UpdateProfilePaper>
        </UpdateProfileContainer>
    );
};

export default UserUpdateProfile;
