import React from 'react';
import { Link as RouterLink } from 'react-router-dom';
import { Drawer, List, ListItem, ListItemIcon, ListItemText } from '@mui/material';
import HomeIcon from '@mui/icons-material/Home';
import BookIcon from '@mui/icons-material/Book';
import HistoryIcon from '@mui/icons-material/History';
import ListAltIcon from '@mui/icons-material/ListAlt';

const Sidebar = () => {
    return (
        <Drawer variant="permanent" sx={{ width: 240 }}>
            <List>
                <ListItem button component={RouterLink} to="/">
                    <ListItemIcon><HomeIcon /></ListItemIcon>
                    <ListItemText primary="Beranda" />
                </ListItem>
                <ListItem button component={RouterLink} to="/peminjaman">
                    <ListItemIcon><BookIcon /></ListItemIcon>
                    <ListItemText primary="Peminjaman" />
                </ListItem>
                <ListItem button>
                    <ListItemIcon><ListAltIcon /></ListItemIcon>
                    <ListItemText primary="Data Stok Buku" />
                </ListItem>
                <ListItem button>
                    <ListItemIcon><BookIcon /></ListItemIcon>
                    <ListItemText primary="Penempatan Buku" />
                </ListItem>
                <ListItem button>
                    <ListItemIcon><HistoryIcon /></ListItemIcon>
                    <ListItemText primary="Riwayat Transaksi Peminjaman" />
                </ListItem>
            </List>
        </Drawer>
    );
};

export default Sidebar;
