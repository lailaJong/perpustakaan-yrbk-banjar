import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { CssBaseline, Box } from '@mui/material';
import Sidebar from './components/Sidebar';
import Beranda from './pages/Beranda';
import Peminjaman from './pages/Peminjaman';

const App = () => {
  return (
    <Router>
      <Box sx={{ display: 'flex' }}>
        <CssBaseline />
        <Sidebar />
        <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
          <Routes>
            <Route path="/" element={<Beranda />} />
            <Route path="/peminjaman" element={<Peminjaman />} />
          </Routes>
        </Box>
      </Box>
    </Router>
  );
};

export default App;
