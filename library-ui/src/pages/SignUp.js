import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { TextField, Button, Typography, Box, Grid, FormControl, InputLabel, Select, MenuItem, Checkbox, FormControlLabel } from '@mui/material';
import { styled } from '@mui/system';
import { LocalizationProvider, DatePicker } from '@mui/x-date-pickers';
import id from 'date-fns/locale/id';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFnsV3';
import backgroundImage from '../assets/backgroud-website.jpg';

const Background = styled('div')({
    backgroundImage: `url(${backgroundImage})`,
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    height: '100vh',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center'
});

const SignUpBox = styled('div')({
    backgroundColor: 'rgba(255, 255, 255, 0.9)',
    padding: '2rem',
    borderRadius: '8px',
    boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)',
    maxWidth: '800px',
    width: '100%'
});

const SignUp = () => {
    const [formData, setFormData] = useState({
        namaLengkap: '',
        username: '',
        password: '',
        nomorHP: '',
        tempatLahir: '',
        tanggalLahir: null,
        jenisKelamin: '',
        alamatTinggal: '',
        agree: false,
    });

    const [isFormValid, setIsFormValid] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        const { namaLengkap, username, password, nomorHP, tempatLahir, tanggalLahir, jenisKelamin, alamatTinggal, agree } = formData;
        const isValid = 
            namaLengkap &&
            username &&
            password &&
            nomorHP &&
            tempatLahir &&
            tanggalLahir &&
            jenisKelamin &&
            alamatTinggal &&
            agree;
        setIsFormValid(isValid);
    }, [formData]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleDateChange = (date) => {
        setFormData((prevData) => ({
            ...prevData,
            tanggalLahir: date,
        }));
    };

    const handleCheckboxChange = (e) => {
        const { name, checked } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: checked,
        }));
    };

    const handleSubmit = () => {
        // Handle form submission logic here
        console.log('Form submitted', formData);
    };

    return (
        <Background>
            <SignUpBox>
                <Typography variant='h4' gutterBottom>
                    Formulir Pendaftaran Anggota
                </Typography>
                <Grid container spacing={2}>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            label='Nama Lengkap'
                            variant='outlined'
                            fullWidth
                            required
                            name='namaLengkap'
                            value={formData.namaLengkap}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            label='Tempat Lahir'
                            variant='outlined'
                            fullWidth
                            required
                            name='tempatLahir'
                            value={formData.tempatLahir}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            label='Username'
                            variant='outlined'
                            fullWidth
                            required
                            name='username'
                            value={formData.username}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <LocalizationProvider dateAdapter={AdapterDateFns} adapterLocale={id}>
                            <DatePicker
                                label='Tanggal Lahir'
                                value={formData.tanggalLahir}
                                onChange={handleDateChange}
                                sx={{width: '100%'}}
                                slotProps={{ textField: { variant: 'outlined', width: '100%'} }}
                                // renderInput={(params) => <TextField {...params} variant='outlined' fullWidth sx={{ width: '100%' }} />}
                            />
                        </LocalizationProvider>
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            label='Password'
                            type='password'
                            variant='outlined'
                            fullWidth
                            required
                            name='password'
                            value={formData.password}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <FormControl fullWidth variant='outlined' required>
                            <InputLabel>Jenis Kelamin</InputLabel>
                            <Select
                                name='jenisKelamin'
                                value={formData.jenisKelamin}
                                onChange={handleChange}
                                label='Jenis Kelamin'
                            >
                                <MenuItem value=''><em>None</em></MenuItem>
                                <MenuItem value='Laki-laki'>Laki-laki</MenuItem>
                                <MenuItem value='Perempuan'>Perempuan</MenuItem>
                            </Select>
                        </FormControl>
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            label='Nomor HP'
                            variant='outlined'
                            fullWidth
                            required
                            name='nomorHP'
                            value={formData.nomorHP}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            label='Alamat Tinggal'
                            variant='outlined'
                            fullWidth
                            required
                            name='alamatTinggal'
                            value={formData.alamatTinggal}
                            onChange={handleChange}
                        />
                    </Grid>
                </Grid>
                <FormControlLabel
                    control={
                        <Checkbox
                            checked={formData.agree}
                            onChange={handleCheckboxChange}
                            name='agree'
                        />
                    }
                    label='Saya menyatakan data yang diisi benar dan dapat dipertanggungjawabkan'
                />
                <Box display='flex' justifyContent='flex-end' alignItems='center' marginTop='1rem'>
                    <Button 
                        variant='contained' 
                        color='primary' 
                        disabled={!isFormValid} 
                        onClick={handleSubmit} 
                        sx={{ marginRight: '1rem' }}
                    >
                        Daftar
                    </Button>
                    <Button variant='contained' color='secondary' onClick={() => navigate('/login')}>
                        Batal
                    </Button>
                </Box>
            </SignUpBox>
        </Background>
    );
};

export default SignUp;