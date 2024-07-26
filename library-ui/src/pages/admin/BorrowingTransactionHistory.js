// src/pages/BorrowingTransactionHistory.js
import React, { useState, useEffect } from 'react';
import { Container, Typography, TextField, InputAdornment, IconButton, Box, Table, TableHead, TableBody, TableRow, TableCell } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import { styled } from '@mui/material/styles';
import api from '../../utility/api';

const StatusText = styled('span')(({ status }) => ({
    color: status === 'Dikembalikan' ? 'green' : 'red',
}));

const BorrowingTransactionHistory = () => {
    const [transactions, setTransactions] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        const fetchTransactions = async () => {
            try {
                const response = await api.get('/api/borrowing-transactions');
                setTransactions(response.data);
            } catch (error) {
                console.error('Error fetching transactions:', error);
            }
        };

        fetchTransactions();
    }, []);

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredTransactions = transactions.filter((transaction) =>
        transaction.borrowerName.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <Container maxWidth="lg">
            <Typography variant="h4" gutterBottom>
                Riwayat Transaksi Peminjaman
            </Typography>
            <Box display="flex" justifyContent="space-between" alignItems="center" marginBottom="16px">
                <TextField
                    variant="outlined"
                    placeholder="cari berdasarkan nama pemustaka"
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
            </Box>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>ID</TableCell>
                        <TableCell>Pemustaka</TableCell>
                        <TableCell>ID Buku</TableCell>
                        <TableCell>Status</TableCell>
                        <TableCell>Tanggal Pinjam</TableCell>
                        <TableCell>Tanggal Kembali</TableCell>
                        <TableCell>Tanggal Selesai</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {filteredTransactions.map((transaction) => (
                        <TableRow key={transaction.id}>
                            <TableCell>{transaction.id}</TableCell>
                            <TableCell>{transaction.borrowerName}</TableCell>
                            <TableCell>{transaction.bookId}</TableCell>
                            <TableCell>
                                <StatusText status={transaction.status}>{transaction.status}</StatusText>
                            </TableCell>
                            <TableCell>{transaction.borrowDate}</TableCell>
                            <TableCell>{transaction.returnDate}</TableCell>
                            <TableCell>{transaction.completedDate}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </Container>
    );
};

export default BorrowingTransactionHistory;