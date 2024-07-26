import React from 'react';
import { Typography, Box } from '@mui/material';
import { styled } from '@mui/material/styles';

const Header = ({ title }) => {
    const Header = styled(Box)(({ theme }) => ({
        backgroundColor: theme.header.backgroundColor,
        padding: '16px',
        marginBottom: '24px',
        borderRadius: '4px',
        boxShadow: theme.shadows[1],
    }));

    const HeaderTitle = styled(Typography)(({ theme }) => ({
        fontFamily: theme.header.font,
    }));

    return (
        <Header>
            <HeaderTitle variant="h4">
                {title}
            </HeaderTitle>
        </Header>
    );
};

export default Header;
