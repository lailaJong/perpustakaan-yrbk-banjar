import React from 'react';
import { Link as RouterLink } from 'react-router-dom';
import { Drawer, List, ListItem, ListItemIcon, ListItemText, IconButton, Box, ListItemButton, Typography } from '@mui/material';
import HomeIcon from '@mui/icons-material/Home';
import BookIcon from '@mui/icons-material/Book';
import HistoryIcon from '@mui/icons-material/History';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import CategoryIcon from '@mui/icons-material/Category';
import LocalLibraryIcon from '@mui/icons-material/LocalLibrary';
import ExitToAppIcon from '@mui/icons-material/ExitToApp';
import logo from '../assets/logo-rbk.jpg'; // Add the appropriate path for your logo
import { styled } from '@mui/material/styles';

const drawerWidth = 240;

const DrawerStyled = styled(Drawer)(({ open }) => ({
  '& .MuiDrawer-paper': {
    width: open ? drawerWidth : 60,
    transition: 'width 0.3s',
    overflowX: 'hidden',
  },
}));

const Logo = styled('img')(({ open }) => ({
  height: open ? '75px' : '40px',
  transition: 'height 0.3s',
}));

const TitleContainer = styled(Box)(({ open }) => ({
  display: 'flex',
  alignItems: 'center',
  justifyContent: open ? 'flex-start' : 'center',
  padding: '8px',
  paddingLeft: open ? '16px' : '8px',
}));

const Title = styled(Typography)({
  marginLeft: '20px',
  whiteSpace: 'normal',
  flexGrow: 1,
});

const Sidebar = ({ open, handleDrawerToggle }) => {
  return (
    <Box sx={{ display: 'flex' }}>
      <DrawerStyled variant="permanent" open={open}>
        <TitleContainer open={open}>
          <IconButton onClick={handleDrawerToggle} sx={{ p: 0 }}>
            <Logo src={logo} alt="Logo" open={open} />
          </IconButton>
          {open && (
            <Title variant="h6">
              Ruang Baca Komunitas
            </Title>
          )}
        </TitleContainer>
        <List>
          <ListItem disablePadding>
            <ListItemButton component={RouterLink} to="/">
              <ListItemIcon><HomeIcon /></ListItemIcon>
              {open && <ListItemText primary="Beranda" />}
            </ListItemButton>
          </ListItem>
          <ListItem disablePadding>
            <ListItemButton component={RouterLink} to="/profile">
              <ListItemIcon><AccountCircleIcon /></ListItemIcon>
              {open && <ListItemText primary="Profil" />}
            </ListItemButton>
          </ListItem>
          <ListItem disablePadding>
            <ListItemButton component={RouterLink} to="/catalog">
              <ListItemIcon><CategoryIcon /></ListItemIcon>
              {open && <ListItemText primary="Katalog" />}
            </ListItemButton>
          </ListItem>
          <ListItem disablePadding>
            <ListItemButton component={RouterLink} to="/peminjaman">
              <ListItemIcon><LocalLibraryIcon /></ListItemIcon>
              {open && <ListItemText primary="Koleksi Dipinjam" />}
            </ListItemButton>
          </ListItem>
          <ListItem disablePadding>
            <ListItemButton component={RouterLink} to="/reserved-books">
              <ListItemIcon><BookIcon /></ListItemIcon>
              {open && <ListItemText primary="Koleksi Dipesan" />}
            </ListItemButton>
          </ListItem>
          <ListItem disablePadding>
            <ListItemButton component={RouterLink} to="/borrow-history">
              <ListItemIcon><HistoryIcon /></ListItemIcon>
              {open && <ListItemText primary="Riwayat Peminjaman" />}
            </ListItemButton>
          </ListItem>
          <ListItem disablePadding>
            <ListItemButton component={RouterLink} to="/logout">
              <ListItemIcon><ExitToAppIcon /></ListItemIcon>
              {open && <ListItemText primary="Log Out" />}
            </ListItemButton>
          </ListItem>
        </List>
      </DrawerStyled>
    </Box>
  );
};

export default Sidebar;
