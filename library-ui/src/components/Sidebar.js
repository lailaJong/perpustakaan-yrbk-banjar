import React from 'react';
import { Link as RouterLink, useLocation } from 'react-router-dom';
import { Drawer, List, ListItem, ListItemIcon, ListItemText, IconButton, Box, ListItemButton, Typography } from '@mui/material';
import HomeIcon from '@mui/icons-material/Home';
import CategoryIcon from '@mui/icons-material/Category';
import HistoryIcon from '@mui/icons-material/History';
import ExitToAppIcon from '@mui/icons-material/ExitToApp';
import StackedBarChartIcon from '@mui/icons-material/StackedBarChart';
import Groups3Icon from '@mui/icons-material/Groups3';
import PrintIcon from '@mui/icons-material/Print';
import HorizontalSplitIcon from '@mui/icons-material/HorizontalSplit';
import logo from '../assets/logo-rbk.jpg'; // Add the appropriate path for your logo
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

const Sidebar = ({ open, handleDrawerToggle }) => {
  const navigate = useNavigate();
  const location = useLocation();

  const handleLogout = () => {
    localStorage.removeItem('token-library-app');
    navigate('/login');
  }

  const menuItems = [
    { text: 'Beranda', icon: <HomeIcon />, link: '/' },
    { text: 'Data Stok Buku', icon: <StackedBarChartIcon />, link: '/admin/books-stock' },
    { text: 'Data Penulis', icon: <Groups3Icon />, link: '/admin/authors' },
    { text: 'Data Penerbit', icon: <PrintIcon />, link: '/admin/publishers' },
    { text: 'Data Kategori', icon: <CategoryIcon />, link: '/admin/books-categories' },
    { text: 'Data Rak Buku', icon: <HorizontalSplitIcon />, link: '/admin/bookshelves' },
    { text: 'Riwayat Transaksi Peminjaman', icon: <HistoryIcon />, link: '/admin/borrowing-transaction-history' },
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
          {/* {menuItems.map((item, index) => (
            item.roles.includes(userRole) && (
              <ListItem key={index} disablePadding>
                <ListItemButton component={RouterLink} to={item.link}>
                  <ListItemIcon>{item.icon}</ListItemIcon>
                  {open && <ListItemText primary={item.text} />}
                </ListItemButton>
              </ListItem>
            )
          ))} */}
          {menuItems.map((item, index) => {
            const isActive = location.pathname === item.link;
            return (
              <ListItem key={index} disablePadding>
                {isActive ? (<ActiveListItemButton component={RouterLink} to={item.link}>
                  <ListItemIcon sx={{ color: 'white' }}>{item.icon}</ListItemIcon>
                  {open && <ListItemText primary={item.text} />}
                </ActiveListItemButton>) : (<ListItemButton component={RouterLink} to={item.link}>
                  <ListItemIcon sx={{ color: 'white' }}>{item.icon}</ListItemIcon>
                  {open && <ListItemText primary={item.text} />}
                </ListItemButton>)}
              </ListItem>
            )
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

export default Sidebar;
