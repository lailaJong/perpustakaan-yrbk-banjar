// src/pages/admin/AddBorrowingTransaction.js
import React, { useState, useEffect } from 'react';
import { Container, Typography, TextField, Button, MenuItem, Box } from '@mui/material';
import { styled } from '@mui/material/styles';
import { useNavigate } from 'react-router-dom';
import api from '../../utility/api';

const FormContainer = styled(Box)(({ theme }) => ({
    backgroundColor: theme.palette.background.paper,
    padding: '24px',
    borderRadius: '8px',
    boxShadow: theme.shadows[2],
}));

const FormRow = styled(Box)(({ theme }) => ({
    display: 'flex',
    flexDirection: 'column',
    marginBottom: '16px',
}));

const ActionButtons = styled(Box)(({ theme }) => ({
    display: 'flex',
    justifyContent: 'flex-end',
    marginTop: '16px',
    gap: '8px',
}));

const AddBorrowingTransaction = () => {
    const navigate = useNavigate();
    const [borrowers, setBorrowers] = useState([]);
    const [books, setBooks] = useState([]);
    const [selectedBorrower, setSelectedBorrower] = useState('');
    const [selectedBook, setSelectedBook] = useState('');

    useEffect(() => {
        const fetchBorrowers = async () => {
            try {
                const response = await api.get('/api/users');
                setBorrowers(response.data);
            } catch (error) {
                console.error('Error fetching borrowers:', error);
            }
        };

        const fetchBooks = async () => {
            try {
                const response = await api.get('/api/books');
                setBooks(response.data);
            } catch (error) {
                console.error('Error fetching books:', error);
            }
        };

        fetchBorrowers();
        fetchBooks();
    }, []);

    const handleSave = async () => {
        try {
            const response = await api.post('/api/borrowing-transactions', {
                borrowerId: selectedBorrower,
                bookId: selectedBook,
            });
            navigate('/admin/borrowing-transaction-list', { state: { showAddTransactionSnackbar: true, idTransaction : response.data.idTransaction } })
        } catch (error) {
            console.error('Error saving transaction:', error);
        }
    };

    const handleCancel = () => {
        navigate('/admin/borrowing-transaction-list');
    };

    return (
        <Container maxWidth="md">
            <Typography variant="h4" gutterBottom>
                Transaksi Peminjaman
            </Typography>
            <FormContainer>
                <FormRow>
                    <TextField
                        select
                        label="Nama Pemustaka"
                        value={selectedBorrower}
                        onChange={(e) => setSelectedBorrower(e.target.value)}
                        variant="outlined"
                        fullWidth
                        margin="normal"
                    >
                        {borrowers.map((borrower) => (
                            <MenuItem key={borrower.id} value={borrower.id}>
                                {borrower.fullName}
                            </MenuItem>
                        ))}
                    </TextField>
                </FormRow>
                <FormRow>
                    <TextField
                        select
                        label="Judul Buku"
                        value={selectedBook}
                        onChange={(e) => setSelectedBook(e.target.value)}
                        variant="outlined"
                        fullWidth
                        margin="normal"
                    >
                        {books.map((book) => (
                            <MenuItem key={book.id} value={book.id}>
                                {book.title}
                            </MenuItem>
                        ))}
                    </TextField>
                </FormRow>
                <ActionButtons>
                    <Button variant="contained" color="secondary" onClick={handleCancel}>
                        Batal
                    </Button>
                    <Button variant="contained" color="primary" onClick={handleSave}>
                        Simpan
                    </Button>
                </ActionButtons>
            </FormContainer>
        </Container>
    );
};

export default AddBorrowingTransaction;
