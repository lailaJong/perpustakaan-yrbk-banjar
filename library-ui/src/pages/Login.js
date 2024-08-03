import React, { useState } from 'react';
import { useNavigate, Link as RouterLink } from 'react-router-dom';
import axios from 'axios';
import { TextField, Button, Typography, Box, Grid, Link } from '@mui/material';
import { styled } from '@mui/system';
import backgroundImage from '../assets/backgroud-website.jpg';
import TitleBanner from '../components/TitleBanner';

const baseUrl = 'http://localhost:3001';

const Background = styled('div')({
    backgroundImage: `url(${backgroundImage})`,
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    height: '100vh',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center'
});

const LoginBox = styled('div')({
    backgroundColor: 'rgba(255, 255, 255, 0.9)',
    padding: '2rem',
    borderRadius: '8px',
    boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)',
    maxWidth: '400px',
    width: '100%'
});

const Login = ({ setToken, setLoading }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        if (email === '' || password === '') {
            setError('Username/Password harus diisi di field tersebut');
            return;
        }
        
        try {
            const response = await axios.post(`${baseUrl}/api/login`, { email, password });
            const token = response.data.token;

            localStorage.setItem('token-library-app', token);
            setToken(token);
            setLoading(true);
            navigate('/');
        } catch (error) {
            console.error('Login failed:', error);
            setError('Login failed. Please try again.');
        }
    };

    const handleKeyPress = (event) => {
        if (event.key === 'Enter') {
            handleLogin();
        }
    };

    return (
        <Background>
            <TitleBanner title={'RUANG BACA KOMUNITAS'} />
            <LoginBox>
                <Typography variant='h4' gutterBottom>
                    Login Keanggotaan
                </Typography>
                <TextField
                    label='Email'
                    variant='outlined'
                    fullWidth
                    margin='normal'
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    onKeyPress={handleKeyPress}
                />
                <TextField
                    label='Password'
                    type='password'
                    variant='outlined'
                    fullWidth
                    margin='normal'
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    onKeyPress={handleKeyPress}
                />
                {error && (
                    <Typography variant='body2' color='error'>
                        {error}
                    </Typography>
                )}
                <Box display='flex' justifyContent='center' alignItems='center' marginTop='1rem'>
                    <Button variant='contained' color='primary' onClick={handleLogin}>
                        Login
                    </Button>
                </Box>
                <Box display='flex' justifyContent='center' alignItems='center' marginTop='1rem'>
                    <Grid container spacing={1} alignItems='center' justifyContent='center'>
                        <Grid item>
                            <Typography variant='body2'>
                                Daftar menjadi anggota
                            </Typography>
                        </Grid>
                        <Grid item>
                            <Link component={RouterLink} to='/terms-and-conditions' variant='body2'>
                                Di sini
                            </Link>
                        </Grid>
                    </Grid>
                </Box>
            </LoginBox>
        </Background>
    );
};

export default Login;
