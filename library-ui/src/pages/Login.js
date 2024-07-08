import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { Container, TextField, Button, Typography, Box, Link } from '@mui/material';
import { styled } from '@mui/system';
import backgroundImage from '../assets/backgroud-website.jpg';

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
    backgroundColor: 'rgba(255, 255, 255, 0.8)',
    padding: '2rem',
    borderRadius: '8px',
    boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)',
    maxWidth: '400px',
    width: '100%'
});

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const response = await axios.post(`${baseUrl}/api/login`, { email, password });
            const token = response.data.token;
            localStorage.setItem('token-library-app', token);
            navigate('/dashboard');
        } catch (error) {
            console.error('Login failed:', error);
        }
    };

    return (
        <Background>
            <LoginBox>
                <Typography variant="h4" gutterBottom>
                    Login Keanggotaan
                </Typography>
                <TextField
                    label="Email"
                    variant="outlined"
                    fullWidth
                    margin="normal"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <TextField
                    label="Password"
                    type="password"
                    variant="outlined"
                    fullWidth
                    margin="normal"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <Box display="flex" justifyContent="space-between" alignItems="center" marginTop="1rem">
                    <Link href="#" variant="body2">
                        Daftar menjadi anggota
                    </Link>
                    <Button variant="contained" color="primary">
                        Daftar
                    </Button>
                    <Button variant="contained" color="primary" onClick={handleLogin}>
                        Login
                    </Button>
                </Box>
            </LoginBox>
        </Background>
    );
};

export default Login;
