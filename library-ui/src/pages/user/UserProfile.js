// src/pages/UserProfile.js
import React, { useState, useEffect } from 'react';
import api from '../../utility/api';
import { Container, Grid, Paper, Typography, Button, Box } from '@mui/material';
import { styled } from '@mui/system';
import BadgeIcon from '@mui/icons-material/Badge';
import { useNavigate } from 'react-router-dom';

const ProfileContainer = styled(Container)(({ theme }) => ({
    marginTop: theme.spacing(4),
}));

const ProfilePaper = styled(Paper)(({ theme }) => ({
    padding: theme.spacing(4),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    textAlign: 'center',
}));

const ProfileHeader = styled('div')(({ theme }) => ({
    marginBottom: theme.spacing(2),
    textAlign: 'center',
}));

const ProfileAvatar = styled('img')(({ theme }) => ({
    width: theme.spacing(25),
    height: theme.spacing(25),
    borderRadius: '50%',
    marginBottom: theme.spacing(2),
    border: `4px solid ${theme.palette.primary.main}`,
}));

const ProfileButton = styled(Button)(({ theme }) => ({
    margin: theme.spacing(1),
}));

const ProfileDetailsPaper = styled(Paper)(({ theme }) => ({
    padding: theme.spacing(3),
}));

const MemberIdBox = styled(Box)(({ theme }) => ({
    display: 'flex',
    alignItems: 'center',
    backgroundColor: '#FFC107', // Yellow background color
    padding: theme.spacing(1),
    borderRadius: theme.shape.borderRadius,
}));

const UserProfile = ({ userId }) => {
    const [profile, setProfile] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        const fetchProfile = async () => {
            try {
                const profileResponse = await api.get(`/api/user-profile/${userId}`);
                setProfile(profileResponse.data);
                console.log(profileResponse);
            } catch (error) {
                console.error('Error fetching profile data:', error);
            }
        };

        fetchProfile();
    }, [userId]);

    return (
        <ProfileContainer maxWidth='lg'>
            <Typography variant='h4' gutterBottom>
                Profil Page
            </Typography>
            <Grid container spacing={3}>
                <Grid item xs={12} md={6}>
                    <ProfilePaper>
                        <ProfileHeader>
                            <ProfileAvatar src={profile.avatarUrl || '../../assets/rabbit-mammal-animals.png'} alt='Profile Avatar' />
                            <Typography variant='h5'>{profile.fullName || 'N/A'}</Typography>
                            <MemberIdBox>
                                <BadgeIcon style={{ marginRight: '8px', color: 'white' }} />
                                <Typography variant='subtitle1' color='white' style={{ flex: 1, textAlign: 'center' }}>{profile.memberId || 'N/A'}</Typography>
                            </MemberIdBox>
                        </ProfileHeader>
                        <div>
                            <ProfileButton variant='contained' color='primary' onClick={() => { navigate('/user/update-password'); }}>
                                Ubah Password
                            </ProfileButton>
                            <ProfileButton variant='contained' color='secondary'onClick={() => { navigate('/user/update-profile'); }}>
                                Edit Profil
                            </ProfileButton>
                        </div>
                    </ProfilePaper>
                </Grid>
                <Grid item xs={12} md={6}>
                    <ProfileDetailsPaper>
                        <Typography variant='h6' gutterBottom>
                            Profil Lengkap
                        </Typography>
                        <Typography>Nama Lengkap: {profile.fullName || 'N/A'}</Typography>
                        <Typography>Username: {profile.username || 'N/A'}</Typography>
                        <Typography>Jenis Kelamin: {profile.gender || 'N/A'}</Typography>
                        <Typography>Nomor HP: {profile.phoneNumber || 'N/A'}</Typography>
                        <Typography>Tempat Lahir: {profile.birthPlace || 'N/A'}</Typography>
                        <Typography>Tanggal Lahir: {profile.birthDate || 'N/A'}</Typography>
                        <Typography>Alamat: {profile.address || 'N/A'}</Typography>
                        <Typography>Tanggal Registrasi: {profile.registrationDate || 'N/A'}</Typography>
                    </ProfileDetailsPaper>
                </Grid>
            </Grid>
        </ProfileContainer>
    );
};

export default UserProfile;
