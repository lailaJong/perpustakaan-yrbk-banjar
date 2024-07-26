import React from 'react';
import { Link as RouterLink, useLocation } from 'react-router-dom';
import { Drawer, List, ListItem, ListItemIcon, ListItemText, IconButton, Box, ListItemButton, Typography } from '@mui/material';
import HomeIcon from '@mui/icons-material/Home';
import BookIcon from '@mui/icons-material/Book';
import HistoryIcon from '@mui/icons-material/History';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import CategoryIcon from '@mui/icons-material/Category';
import LocalLibraryIcon from '@mui/icons-material/LocalLibrary';
import ExitToAppIcon from '@mui/icons-material/ExitToApp';
import logo from '../assets/logo-rbk.jpg';
import { styled } from '@mui/material/styles';
import { useNavigate } from 'react-router-dom';

const drawerWidth = 240;

const DrawerStyled = styled(Drawer)(({ open, theme }) => ({
  '& .MuiDrawer-paper': {
    width: open ? drawerWidth : 60,
    transition: 'width 0.3s',
    overflowX: 'hidden',
    backgroundColor: theme.palette.primary.main,
    color: 'white',
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
  color: 'white',
});

const ActiveListItemButton = styled(ListItemButton)(({ theme }) => ({
  backgroundColor: '#69503a',
  '&:hover': {
    backgroundColor: '#7B564B',
  },
}));

const UserSidebar = ({ open, handleDrawerToggle, userRole }) => {
  const navigate = useNavigate();
  const location = useLocation();

  const handleLogout = () => {
    localStorage.removeItem('token-library-app');
    navigate('/login');
  }

  const menuItems = [
    { text: 'Beranda', icon: <HomeIcon />, link: '/' },
    { text: 'Profil', icon: <AccountCircleIcon />, link: '/user/profile' },
    { text: 'Katalog', icon: <CategoryIcon />, link: '/user/catalog' },
    { text: 'Koleksi Dipinjam', icon: <LocalLibraryIcon />, link: '/user/borrowing-collection' },
    { text: 'Koleksi Dipesan', icon: <BookIcon />, link: '/user/ordering-collection' },
    { text: 'Riwayat Peminjaman', icon: <HistoryIcon />, link: '/user/borrowing-history' },
  ];

  return (
    <Box sx={{ display: 'flex' }}>
      <DrawerStyled variant='permanent' open={open}>
        <TitleContainer open={open}>
          <IconButton onClick={handleDrawerToggle} sx={{ p: 0 }}>
            <Logo src={logo} alt='Logo' open={open} />
          </IconButton>
          {open && (
            <Title variant='h6'>
              Ruang Baca Komunitas
            </Title>
          )}
        </TitleContainer>
        <List>
          {menuItems.map((item, index) => {
            const isActive = location.pathname === item.link;
            return (
              <ListItem key={index} disablePadding>
                {isActive ? (
                  <ActiveListItemButton component={RouterLink} to={item.link}>
                    <ListItemIcon sx={{ color: 'white' }}>{item.icon}</ListItemIcon>
                    {open && <ListItemText primary={item.text} />}
                  </ActiveListItemButton>
                ) : (
                  <ListItemButton component={RouterLink} to={item.link}>
                    <ListItemIcon sx={{ color: 'white' }}>{item.icon}</ListItemIcon>
                    {open && <ListItemText primary={item.text} />}
                  </ListItemButton>
                )}
              </ListItem>
            );
          })}
          <ListItem disablePadding>
            <ListItemButton onClick={handleLogout}>
              <ListItemIcon sx={{ color: 'white' }}><ExitToAppIcon /></ListItemIcon>
              {open && <ListItemText primary='Log Out' />}
            </ListItemButton>
          </ListItem>
        </List>
      </DrawerStyled>
    </Box>
  );
};

export default UserSidebar;