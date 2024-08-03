import React, { useState, useEffect } from 'react';
import api from '../../utility/api';
import { Container, Typography, TextField, InputAdornment, IconButton } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import { styled } from '@mui/material/styles';

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

const UserBorrowingCollection = () => {
    const [loans, setLoans] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        const fetchLoans = async () => {
            try {
                const response = await api.get('/api/loans');
                setLoans(response.data);
            } catch (error) {
                console.error('Error fetching loans:', error);
            }
        };

        fetchLoans();
    }, []);

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredLoans = loans.filter((loan) =>
        loan.title.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <Container maxWidth="lg">
            <Typography variant="h4" gutterBottom>
                Koleksi Dipinjam
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
                        <InputAdornment position="start">
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
                        <OrderTableHeader>Tanggal Peminjaman</OrderTableHeader>
                        <OrderTableHeader>Tanggal Pengambilan</OrderTableHeader>
                    </OrderTableRow>
                </thead>
                <tbody>
                    {filteredLoans.map((loan) => (
                        <OrderTableRow key={loan.id}>
                            <OrderTableCell>{loan.id}</OrderTableCell>
                            <OrderTableCell>{loan.title}</OrderTableCell>
                            <OrderTableCell style={{ color: loan.status === 'Dipesan' ? 'green' : 'red' }}>{loan.status}</OrderTableCell>
                            <OrderTableCell>{loan.borrowDate}</OrderTableCell>
                            <OrderTableCell>{loan.returnDate}</OrderTableCell>
                        </OrderTableRow>
                    ))}
                </tbody>
            </OrderTable>
        </Container>
    );
};

export default UserBorrowingCollection;
