import React from 'react';
import { Box, Typography, Button } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';

const Unauthorized = () => {
  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        height: '100vh',
      }}
    >
      <Typography variant='h4' component='h1' gutterBottom>
        403 - Unauthorized
      </Typography>
      <Typography variant='body1' gutterBottom>
        You do not have permission to view this page.
      </Typography>
      <Button variant='contained' color='primary' component={RouterLink} to='/'>
        Go to Home
      </Button>
    </Box>
  );
};

export default Unauthorized;
