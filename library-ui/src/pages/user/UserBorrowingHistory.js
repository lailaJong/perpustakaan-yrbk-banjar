// src/pages/UserBorrowingHistory.js
import React, { useState, useEffect } from 'react';
import { Container, Typography, TextField, InputAdornment, IconButton } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import { styled } from '@mui/material/styles';
import api from '../../utility/api';

const BorrowingTable = styled('table')({
    width: '100%',
    borderCollapse: 'collapse',
    marginTop: '16px',
});

const BorrowingTableHeader = styled('th')({
    border: '1px solid #ddd',
    padding: '8px',
    textAlign: 'left',
    backgroundColor: '#f2f2f2',
});

const BorrowingTableRow = styled('tr')({
    border: '1px solid #ddd',
    padding: '8px',
    '&:nth-of-type(even)': {
        backgroundColor: '#f9f9f9',
    },
});

const BorrowingTableCell = styled('td')({
    border: '1px solid #ddd',
    padding: '8px',
    textAlign: 'left',
});

const UserBorrowingHistory = () => {
    const [borrowings, setBorrowings] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        const fetchBorrowings = async () => {
            try {
                const response = await api.get('/api/borrowings');
                setBorrowings(response.data);
            } catch (error) {
                console.error('Error fetching borrowings:', error);
            }
        };

        fetchBorrowings();
    }, []);

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredBorrowings = borrowings.filter((borrowing) =>
        borrowing.title.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <Container maxWidth="lg">
            <Typography variant="h4" gutterBottom>
                Riwayat Peminjaman
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
            <BorrowingTable>
                <thead>
                    <BorrowingTableRow>
                        <BorrowingTableHeader>ID</BorrowingTableHeader>
                        <BorrowingTableHeader>Judul</BorrowingTableHeader>
                        <BorrowingTableHeader>Tanggal Pinjam</BorrowingTableHeader>
                        <BorrowingTableHeader>Tanggal Kembali</BorrowingTableHeader>
                        <BorrowingTableHeader>Tanggal Selesai</BorrowingTableHeader>
                    </BorrowingTableRow>
                </thead>
                <tbody>
                    {filteredBorrowings.map((borrowing) => (
                        <BorrowingTableRow key={borrowing.id}>
                            <BorrowingTableCell>{borrowing.id}</BorrowingTableCell>
                            <BorrowingTableCell>{borrowing.title}</BorrowingTableCell>
                            <BorrowingTableCell>{borrowing.borrowDate}</BorrowingTableCell>
                            <BorrowingTableCell>{borrowing.returnDate}</BorrowingTableCell>
                            <BorrowingTableCell>{borrowing.completedDate}</BorrowingTableCell>
                        </BorrowingTableRow>
                    ))}
                </tbody>
            </BorrowingTable>
        </Container>
    );
};

export default UserBorrowingHistory;
