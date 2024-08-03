import React from 'react';
import { Typography, Checkbox, FormControlLabel, Button, Box, Grid } from '@mui/material';
import { styled } from '@mui/system';
import { useNavigate } from 'react-router-dom';
import backgroundImage from '../assets/backgroud-website.jpg';
import TitleBanner from '../components/TitleBanner';

const Background = styled('div')({
    backgroundImage: `url(${backgroundImage})`,
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    height: '100vh',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    position: 'relative'
});

const TermBox = styled('div')({
    backgroundColor: 'rgba(255, 255, 255, 0.9)',
    padding: '2rem',
    borderRadius: '8px',
    boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)',
    maxWidth: '800px',
    width: '100%',
    textAlign: 'left'
});

const TermsAndConditions = () => {
    const navigate = useNavigate();
    const [checked, setChecked] = React.useState(false);

    const handleCheckboxChange = (event) => {
        setChecked(event.target.checked);
    };

    const handleContinue = () => {
        if (checked) {
            navigate('/signup');  // Adjust the path as needed for the next step in your process
        }
    };

    return (
        <Background>
            <TitleBanner title={'RUANG BACA KOMUNITAS'} />
            <TermBox>
                <Typography variant='h4' gutterBottom sx={{textAlign: 'center'}}>
                    Persyaratan Pendaftaran
                </Typography>
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <Typography variant='h6'>Persyaratan</Typography>
                        <Typography variant='body1'>
                            Saya telah membaca & menyetujui atas persyaratan dan kondisi yang berlaku.
                        </Typography>
                    </Grid>
                    <Grid item xs={12}>
                        <Typography variant='h6'>Tata Tertib</Typography>
                        <Typography variant='body1'>
                            Saya telah membaca & menyetujui atas persyaratan dan kondisi yang berlaku.
                        </Typography>
                    </Grid>
                    <Grid item xs={12}>
                        <Typography variant='h6'>Kartu Anggota</Typography>
                        <Typography variant='body1'>
                            Saya telah membaca & menyetujui atas persyaratan dan kondisi yang berlaku.
                        </Typography>
                    </Grid>
                    <Grid item xs={12}>
                        <Typography variant='h6'>Hak Dan Kewajiban</Typography>
                        <Typography variant='body1'>
                            Saya telah membaca & menyetujui atas persyaratan dan kondisi yang berlaku.
                        </Typography>
                    </Grid>
                </Grid>
                <FormControlLabel
                    control={
                        <Checkbox
                            checked={checked}
                            onChange={handleCheckboxChange}
                            name='agree'
                        />
                    }
                    label='Saya telah membaca & menyetujui atas persyaratan dan kondisi yang berlaku.'
                    sx={{ marginTop: '1rem' }}
                />
                <Box display='flex' justifyContent='center' alignItems='center' marginTop='1rem'>
                    <Button
                        variant='contained'
                        color='primary'
                        onClick={handleContinue}
                        disabled={!checked}
                        sx={{ marginRight: '1rem' }}
                    >
                        Lanjutkan Pendaftaran
                    </Button>
                </Box>
            </TermBox>
        </Background>
    );
};

export default TermsAndConditions;
