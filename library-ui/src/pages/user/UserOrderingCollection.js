// src/pages/UserOrderingCollection.js
import React, { useState, useEffect } from 'react';
import { Container, Typography, Button, TextField, InputAdornment, IconButton } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import { styled } from '@mui/material/styles';
import api from '../../utility/api';

const OrderTable = styled('table')({
    width: '100%',
    borderCollapse: 'collapse',
    marginTop: '16px',
});

const OrderTableHeader = styled('th')({
    border: '1px solid #ddd',
    padding: '8px',
    textAlign: 'left',
    backgroundColor: '#f2f2f2',
});

const OrderTableRow = styled('tr')({
    border: '1px solid #ddd',
    padding: '8px',
    '&:nth-of-type(even)': {
        backgroundColor: '#f9f9f9',
    },
});

const OrderTableCell = styled('td')({
    border: '1px solid #ddd',
    padding: '8px',
    textAlign: 'left',
});

const UserOrderingCollection = () => {
    const [orders, setOrders] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        const fetchOrders = async () => {
            try {
                const response = await api.get('/api/orders');
                setOrders(response.data);
            } catch (error) {
                console.error('Error fetching orders:', error);
            }
        };

        fetchOrders();
    }, []);

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredOrders = orders.filter((order) =>
        order.title.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <Container maxWidth="lg">
            <Typography variant="h4" gutterBottom>
                Koleksi Dipesan
            </Typography>
            <TextField
                variant="outlined"
                placeholder="cari buku berdasarkan judul"
                fullWidth
                margin="normal"
                value={searchTerm}
                onChange={handleSearch}
                InputProps={{
                    endAdornment: (
                        <InputAdornment position="end">
                            <IconButton>
                                <SearchIcon />
                            </IconButton>
                        </InputAdornment>
                    ),
                }}
            />
            <OrderTable>
                <thead>
                    <OrderTableRow>
                        <OrderTableHeader>ID</OrderTableHeader>
                        <OrderTableHeader>Judul</OrderTableHeader>
                        <OrderTableHeader>Status</OrderTableHeader>
                        <OrderTableHeader>Waktu Pemesanan</OrderTableHeader>
                        <OrderTableHeader>Waktu Pengambilan</OrderTableHeader>
                        <OrderTableHeader>Aksi</OrderTableHeader>
                    </OrderTableRow>
                </thead>
                <tbody>
                    {filteredOrders.map((order) => (
                        <OrderTableRow key={order.id}>
                            <OrderTableCell>{order.id}</OrderTableCell>
                            <OrderTableCell>{order.title}</OrderTableCell>
                            <OrderTableCell style={{ color: order.status === 'Dipesan' ? 'green' : 'red' }}>{order.status}</OrderTableCell>
                            <OrderTableCell>{order.orderDate}</OrderTableCell>
                            <OrderTableCell>{order.pickupDate}</OrderTableCell>
                            <OrderTableCell>
                                <Button variant="contained" color="secondary">
                                    Batalkan
                                </Button>
                            </OrderTableCell>
                        </OrderTableRow>
                    ))}
                </tbody>
            </OrderTable>
        </Container>
    );
};

export default UserOrderingCollection;