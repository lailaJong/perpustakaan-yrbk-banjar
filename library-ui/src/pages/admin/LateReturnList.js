// src/pages/LateReturnList.js
import React, { useState, useEffect } from 'react';
import { Container, Typography, TextField, InputAdornment, IconButton, Box, Button, Snackbar, Alert } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import CheckIcon from '@mui/icons-material/Check';
import ClearIcon from '@mui/icons-material/Clear';
import { styled } from '@mui/material/styles';
import api from '../../utility/api';
import EditLateReturnStatusDialog from './EditLateReturnStatusDialog';

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

const LateReturnList = () => {
    const [transactions, setTransactions] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [selectedTransaction, setSelectedTransaction] = useState({
        id: '',
        status: '',
    });
    const [isDialogOpen, setDialogOpen] = useState(false);
    const [isSnackbarOpen, setSnackbarOpen] = useState(false);

    useEffect(() => {
        const fetchTransactions = async () => {
            try {
                const response = await api.get('/api/late-returns');
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

    const handleStatusChange = (transaction, status) => {
        transaction.status = status;
        setSelectedTransaction(transaction);
        setDialogOpen(true);
    };

    const handleDialogClose = () => {
        setDialogOpen(false);
    };

    const handleSaveSuccess = () => {
        setSnackbarOpen(true);
        handleDialogClose();
    };

    const handleSnackbarClose = () => {
        setSnackbarOpen(false);
    };

    return (
        <Container maxWidth="lg">
            <Typography variant="h4" gutterBottom>
                Daftar Buku Telat Kembali
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
            <TransactionTable>
                <thead>
                    <TransactionTableRow>
                        <TransactionTableHeader>ID</TransactionTableHeader>
                        <TransactionTableHeader>Pemustaka</TransactionTableHeader>
                        <TransactionTableHeader>ID Buku</TransactionTableHeader>
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
                open={isSnackbarOpen}
                autoHideDuration={6000}
                onClose={handleSnackbarClose}
                anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
            >
                <Alert onClose={handleSnackbarClose} severity="success" sx={{ width: '100%' }}>
                    {`Transaksi ${selectedTransaction.id} telah dinyatakan ${selectedTransaction.status}!`}
                </Alert>
            </Snackbar>
            <EditLateReturnStatusDialog
                open={isDialogOpen}
                onClose={handleDialogClose}
                onSave={handleSaveSuccess}
                transaction={selectedTransaction}
            />
        </Container>
    );
};

export default LateReturnList;
