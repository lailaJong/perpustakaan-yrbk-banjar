// src/pages/Welcome.js
import React from 'react';
import { Typography, Button, Container, Grid } from '@mui/material';
import { styled } from '@mui/material/styles';
import backgroundImage from '../assets/backgroud-website.jpg';
import logo from '../assets/logo-rbk.jpg';
import { useNavigate } from 'react-router-dom';

const Background = styled('div')({
    backgroundImage: `url(${backgroundImage})`,
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    height: '100vh',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    color: 'white',
    position: 'relative',
});

const Overlay = styled('div')({
    backgroundColor: 'rgba(0, 0, 0, 0.6)',
    width: '100%',
    height: '100%',
    position: 'absolute',
    top: 0,
    left: 0,
});

const Content = styled(Container)({
    position: 'relative',
    zIndex: 1,
});

const LoginButton = styled(Button)({
    backgroundColor: '#000000',
    color: 'white',
    marginTop: '20px',
    '&:hover': {
        backgroundColor: '#333333',
    },
});

const Welcome = ({ setToken }) => {
    const navigate = useNavigate();

    const handleLogin = async () => {
        setToken(null);
        localStorage.removeItem('token-library-app');
        navigate('/login');
    };

    return (
        <Background>
            <Overlay />
            <Content maxWidth="lg">
                <Grid container spacing={2} justifyContent="center">
                    <Grid item xs={12} sm={6}>
                        <Typography variant="h1" gutterBottom sx={{ fontFamily: 'Impact, sans-serif' }}>
                            RUANG BACA KOMUNITAS
                        </Typography>
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <Grid container spacing={2} justifyContent="center">
                            <Grid item xs={12} sm={6}>
                                <img src={logo} alt="Perpustkaan YRBK" style={{ width: '100%' }} />
                            </Grid>
                            <Grid item xs={12} sm={6}>
                                <img src={logo} alt="Perpustkaan YRBK" style={{ width: '100%' }} />
                            </Grid>
                        </Grid>
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <LoginButton variant="contained" onClick={handleLogin}>
                            Login keanggotaan
                        </LoginButton>
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <Typography variant="h6" gutterBottom sx={{ marginTop: '20px' }}>
                            Taman Bacaan Masyarakat Kota Banjar
                        </Typography>
                        <Typography variant="body1">
                            <strong>Alamat:</strong><br />
                            Gg. Asem No.299, RT.03/RW.08, Banjar, Kec. Banjar, Kota Banjar, Jawa Barat 46311
                        </Typography>
                        <Typography variant="body1" sx={{ marginTop: '20px' }}>
                            <strong>Jam Buka:</strong><br />
                            Jumat: 08.00-11.00, 13.00-17.00<br />
                            Sabtu-Kamis: 08.00-17.00
                        </Typography>
                    </Grid>
                </Grid>
            </Content>
        </Background>
    );
};

export default Welcome;
