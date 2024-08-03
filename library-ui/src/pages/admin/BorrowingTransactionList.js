// src/pages/BorrowingTransactionHistory.js
import React, { useState, useEffect } from 'react';
import { Container, Typography, TextField, InputAdornment, IconButton, Box, Button, Snackbar, Alert } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import AddIcon from '@mui/icons-material/Add';
import CheckIcon from '@mui/icons-material/Check';
import ClearIcon from '@mui/icons-material/Clear';
import { styled } from '@mui/material/styles';
import { useNavigate, useLocation } from 'react-router-dom';
import api from '../../utility/api';

const TransactionTable = styled('table')({
    width: '100%',
    borderCollapse: 'collapse',
    marginTop: '16px',
});

const TransactionTableHeader = styled('th')({
    border: '1px solid #ddd',
    padding: '8px',
    backgroundColor: '#f2f2f2',
});

const TransactionTableRow = styled('tr')({
    border: '1px solid #ddd',
    padding: '8px',
    '&:nth-of-type(even)': {
        backgroundColor: '#f9f9f9',
    },
});

const TransactionTableCell = styled('td')({
    border: '1px solid #ddd',
    padding: '8px',
    textAlign: 'center',
});

const StatusButton = styled(Button)(({ status }) => ({
    backgroundColor: status === 'Hilang' ? '#F44336' : '#8BC34A',
    color: 'white',
    '&:hover': {
        backgroundColor: status === 'Hilang' ? '#E53935' : '#7CB342',
    },
}));

const BorrowingTransactionList = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const [transactions, setTransactions] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [selectedTransaction, setSelectedTransaction] = useState({
        id: '',
        status: '',
    });
    const [isSnackbarStatusOpen, setSnackbarStatusOpen] = useState(false);
    const [isSnackbarAddTransactionOpen, setSnackbarAddTransactionOpen] = useState(false);

    useEffect(() => {
        const fetchTransactions = async () => {
            try {
                if (location.state?.showAddTransactionSnackbar) {
                    setSnackbarAddTransactionOpen(true);
                }
                const response = await api.get('/api/borrowing-transactions');
                setTransactions(response.data);
            } catch (error) {
                console.error('Error fetching transactions:', error);
            }
        };

        fetchTransactions();
    }, [location]);

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredTransactions = transactions.filter((transaction) =>
        transaction.borrowerName.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const handleStatusChange = (transaction, status) => {
        transaction.status = status;
        setSelectedTransaction(transaction)
        setSnackbarStatusOpen(true);
    };

    const handleSnackbarStatusClose = () => {
        setSnackbarStatusOpen(false);
        // setSelectedTransaction(null);
    };

    const handleSnackbarAddTransactionClose = () => {
        setSnackbarAddTransactionOpen(false);
    };

    return (
        <Container maxWidth="lg">
            <Typography variant="h4" gutterBottom>
                Daftar Transaksi Peminjaman
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
                <Box marginLeft="16px">
                    <Button variant="contained" startIcon={<AddIcon />} color="addItem" onClick={() => { navigate('/admin/borrowing-transaction-list/add') }}>
                        Tambah Peminjaman
                    </Button>
                </Box>
            </Box>
            <TransactionTable>
                <thead>
                    <TransactionTableRow>
                        <TransactionTableHeader>ID</TransactionTableHeader>
                        <TransactionTableHeader>Pemustaka</TransactionTableHeader>
                        <TransactionTableHeader>ID Buku</TransactionTableHeader>
                        <TransactionTableHeader>Status</TransactionTableHeader>
                        <TransactionTableHeader>Tanggal Pinjam</TransactionTableHeader>
                        <TransactionTableHeader>Tanggal Kembali</TransactionTableHeader>
                        <TransactionTableHeader>Aksi</TransactionTableHeader>
                    </TransactionTableRow>
                </thead>
                <tbody>
                    {filteredTransactions.map((transaction) => (
                        <TransactionTableRow key={transaction.id}>
                            <TransactionTableCell>{transaction.id}</TransactionTableCell>
                            <TransactionTableCell>{transaction.borrowerName}</TransactionTableCell>
                            <TransactionTableCell>{transaction.bookId}</TransactionTableCell>
                            <TransactionTableCell style={{ color: transaction.status === 'Hilang' ? '#F44336' : '#4CAF50' }}>
                                {transaction.status}
                            </TransactionTableCell>
                            <TransactionTableCell>{transaction.borrowDate}</TransactionTableCell>
                            <TransactionTableCell>{transaction.returnDate}</TransactionTableCell>
                            <TransactionTableCell>
                                <Box display="flex" gap="8px" justifyContent="center">
                                    <StatusButton
                                        variant="contained"
                                        status="Hilang"
                                        onClick={() => handleStatusChange(transaction, 'Hilang')}
                                        startIcon={<ClearIcon />}
                                    >
                                        Hilang
                                    </StatusButton>
                                    <StatusButton
                                        variant="contained"
                                        status="Selesai"
                                        onClick={() => handleStatusChange(transaction, 'Selesai')}
                                        startIcon={<CheckIcon />}
                                    >
                                        Selesai
                                    </StatusButton>
                                </Box>
                            </TransactionTableCell>
                        </TransactionTableRow>
                    ))}
                </tbody>
            </TransactionTable>
            <Snackbar
                open={isSnackbarStatusOpen}
                autoHideDuration={6000}
                onClose={handleSnackbarStatusClose}
                anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
            >
                <Alert onClose={handleSnackbarStatusClose} severity="success" sx={{ width: '100%' }}>
                    {`Transaksi ${selectedTransaction?.id} ditanyatakan ${selectedTransaction?.status}`}
                </Alert>
            </Snackbar>
            <Snackbar
                open={isSnackbarAddTransactionOpen}
                autoHideDuration={6000}
                onClose={handleSnackbarAddTransactionClose}
                anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
            >
                <Alert severity="success" sx={{ width: '100%' }}>
                    Berhasil melakukan peminjaman koleksi dengan id : {location.state?.idTransaction ? location.state?.idTransaction : 'xxx'}
                </Alert>
            </Snackbar>
        </Container>
    );
};

export default BorrowingTransactionList;
