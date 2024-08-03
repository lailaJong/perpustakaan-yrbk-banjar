// src/pages/Welcome.js
import React from 'react';
import { Typography, Button, Container, Grid } from '@mui/material';
import { styled } from '@mui/material/styles';
import backgroundImage from '../assets/backgroud-website.jpg';
import perpusImg1 from '../assets/perpustakaan-1.jpg';
import perpusImg2 from '../assets/perpustakaan-2.jpg';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
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
    color: 'white',
    marginTop: '20px',
});

const SliderContainer = styled('div')({
    width: '700px',
    height: '100%',
    margin: '0 auto',
});

const StyledImage = styled('img')({
    width: '700px',
    height: '500px',
    objectFit: 'cover',
});

const images = [perpusImg1, perpusImg2];

const Welcome = ({ setToken }) => {
    const navigate = useNavigate();

    const handleLogin = async () => {
        setToken(null);
        localStorage.removeItem('token-library-app');
        navigate('/login');
    };

    const settings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 3000,
    };

    return (
        <Background>
            <Overlay />
            <Content maxWidth="lg">
                <Grid container spacing={2} justifyContent="center">
                    <Grid item xs={12} sm={6}>
                        <Typography variant="h1" gutterBottom sx={{ fontFamily: 'Salsa, sans-serif' }}>
                            RUANG BACA KOMUNITAS
                        </Typography>
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <SliderContainer>
                            <Slider {...settings}>
                                {images.map((img, index) => (
                                    <div key={index}>
                                        <StyledImage src={img} alt={`Slide ${index + 1}`} />
                                    </div>
                                ))}
                            </Slider>
                        </SliderContainer>
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <LoginButton variant="contained" color='primary' onClick={handleLogin}>
                            <Typography variant='h6'>
                                Login keanggotaan
                            </Typography>
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
