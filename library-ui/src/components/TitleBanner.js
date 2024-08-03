import React from 'react';
import { Typography, Box } from '@mui/material';
import { styled } from '@mui/material/styles';

const TitleBanner = ({ title }) => {
    const TrapezoidBanner = styled(Box)(({ theme }) => ({
        backgroundColor: theme.banner.backgroundColor,
        padding: '16px',
        marginBottom: '24px',
        position: 'absolute',
        top: '16px',
        left: '16px',
        minWidth: '500px',
        width: 'auto',
        height: 'fit-content',
        clipPath: 'polygon(0 0, 100% 0, 85% 100%, 0 100%)'
    }));

    const Title = styled(Typography)(({ theme }) => ({
        fontFamily: theme.banner.font,
        fontWeight: 'bold',
        fontSize: '34px',
        color: 'black',
        zIndex: 10
    }));

    return (
        <TrapezoidBanner>
            <Title variant="h4">
                {title}
            </Title>
        </TrapezoidBanner>
    );
};

export default TitleBanner;
