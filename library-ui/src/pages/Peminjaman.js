import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Container, Typography, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Button, TextField, InputAdornment } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import AddIcon from '@mui/icons-material/Add';

const Peminjaman = () => {
    const [loans, setLoans] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        const fetchLoans = async () => {
            try {
                const response = await axios.get('/api/loans');
                setLoans(response.data);
            } catch (error) {
                console.error('Error fetching loans:', error);
            }
        };

        fetchLoans();
    }, []);

    return (
        <Container maxWidth="lg">
            <Typography variant="h4" gutterBottom>
                Peminjaman
            </Typography>
            <Button variant="contained" color="primary" startIcon={<AddIcon />} sx={{ marginBottom: 2 }}>
                Tambah Peminjaman
            </Button>
            <TextField
                variant="outlined"
                placeholder="cari berdasarkan nama pemustaka"
                InputProps={{
                    startAdornment: (
                        <InputAdornment position="start">
                            <SearchIcon />
                        </InputAdornment>
                    ),
                }}
                sx={{ marginBottom: 3 }}
                fullWidth
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
            />
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Pemustaka</TableCell>
                            <TableCell>ID Buku</TableCell>
                            <TableCell>Status</TableCell>
                            <TableCell>Tanggal Pinjam</TableCell>
                            <TableCell>Tanggal Kembali</TableCell>
                            <TableCell>Aksi</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {loans.filter(loan => loan.name.toLowerCase().includes(searchTerm.toLowerCase())).map((loan, index) => (
                            <TableRow key={index}>
                                <TableCell>{index + 1}</TableCell>
                                <TableCell>{loan.name}</TableCell>
                                <TableCell>{loan.bookId}</TableCell>
                                <TableCell>
                                    <Typography color={loan.status === 'Telat' ? 'error' : 'success'}>
                                        {loan.status}
                                    </Typography>
                                </TableCell>
                                <TableCell>{loan.borrowDate}</TableCell>
                                <TableCell>{loan.returnDate}</TableCell>
                                <TableCell>
                                    <Button variant="contained" color="success">Selesai</Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Container>
    );
};

export default Peminjaman;
