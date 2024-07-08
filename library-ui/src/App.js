import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import { CssBaseline, Box } from '@mui/material';
import Sidebar from './components/Sidebar';
import Beranda from './pages/Beranda';
import Peminjaman from './pages/Peminjaman';
import Login from './pages/Login';
import ProtectedRoute from './components/ProtectedRoute';
import { styled } from '@mui/material/styles';

const MainContent = styled(Box)(({ open }) => ({
  flexGrow: 1,
  padding: '24px',
  marginLeft: open ? 240 : 60,
  transition: 'margin-left 0.3s',
}));

const App = () => {
  const [open, setOpen] = useState(true);

  const handleDrawerToggle = () => {
    setOpen(!open);
  };

  return (
    <Router>
      <CssBaseline />
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<Navigate to="/dashboard" />} />
        <Route
          path="/dashboard"
          element={<ProtectedRoute element={() => <ProtectedLayout component={<Beranda />} open={open} handleDrawerToggle={handleDrawerToggle} />} />}
        />
        <Route
          path="/peminjaman"
          element={<ProtectedRoute element={() => <ProtectedLayout component={<Peminjaman />} open={open} handleDrawerToggle={handleDrawerToggle} />} />}
        />
      </Routes>
    </Router>
  );
};

const ProtectedLayout = ({ component: Component, open, handleDrawerToggle }) => {
  return (
    <Box sx={{ display: 'flex' }}>
      <Sidebar open={open} handleDrawerToggle={handleDrawerToggle} />
      <MainContent open={open}>
        {Component}
      </MainContent>
    </Box>
  );
};

export default App;
