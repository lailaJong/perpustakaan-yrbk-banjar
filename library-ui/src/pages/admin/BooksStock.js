// src/pages/BooksStock.js
import React, { useState, useEffect } from 'react';
import { Container, Typography, TextField, InputAdornment, IconButton, Box, Button, Snackbar } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import { styled } from '@mui/material/styles';
import EditBookStockDialog from './EditBookStockDialog'; // Import the dialog component
import api from '../../utility/api';

const StockTable = styled('table')({
  width: '100%',
  borderCollapse: 'collapse',
  marginTop: '16px',
});

const StockTableHeader = styled('th')({
  border: '1px solid #ddd',
  padding: '8px',
  textAlign: 'left',
  backgroundColor: '#f2f2f2',
});

const StockTableRow = styled('tr')({
  border: '1px solid #ddd',
  padding: '8px',
  '&:nth-of-type(even)': {
    backgroundColor: '#f9f9f9',
  },
});

const StockTableCell = styled('td')({
  border: '1px solid #ddd',
  padding: '8px',
  textAlign: 'left',
});

const EditButton = styled(Button)({
  backgroundColor: '#8BC34A',
  color: 'white',
  '&:hover': {
    backgroundColor: '#7CB342',
  },
});

const DeleteButton = styled(Button)({
  backgroundColor: '#F44336',
  color: 'white',
  '&:hover': {
    backgroundColor: '#E53935',
  },
});

const BooksStock = () => {
  const [books, setBooks] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedBook, setSelectedBook] = useState(null); // For the dialog
  const [isDialogOpen, setDialogOpen] = useState(false); // Dialog open state
  const [isSnackbarOpen, setSnackbarOpen] = useState(false); // Snackbar open state

  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const response = await api.get('/api/books/stock');
        setBooks(response.data);
      } catch (error) {
        console.error('Error fetching books:', error);
      }
    };

    fetchBooks();
  }, []);

  const handleSearch = (event) => {
    setSearchTerm(event.target.value);
  };

  const filteredBooks = books.filter((book) =>
    book.title.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleEdit = (book) => {
    setSelectedBook(book);
    setDialogOpen(true);
  };

  const handleDelete = (bookId) => {
    // Implement delete functionality
    console.log('Delete book', bookId);
  };

  const handleDialogClose = () => {
    setDialogOpen(false);
  };

  const handleSnackbarClose = () => {
    setSnackbarOpen(false);
    setSelectedBook(null);
  };

  const handleSaveSuccess = () => {
    setSnackbarOpen(true);
    handleDialogClose();
  };

  return (
    <Container maxWidth="lg">
      <Typography variant="h4" gutterBottom>
        Data Stok Buku
      </Typography>
      <TextField
        variant="outlined"
        placeholder="cari berdasarkan judul buku"
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
      <StockTable>
        <thead>
          <StockTableRow>
            <StockTableHeader>ID</StockTableHeader>
            <StockTableHeader>ID Buku</StockTableHeader>
            <StockTableHeader>Judul</StockTableHeader>
            <StockTableHeader>Total Stok</StockTableHeader>
            <StockTableHeader>Aksi</StockTableHeader>
          </StockTableRow>
        </thead>
        <tbody>
          {filteredBooks.map((book) => (
            <StockTableRow key={book.id}>
              <StockTableCell>{book.id}</StockTableCell>
              <StockTableCell>{book.bookId}</StockTableCell>
              <StockTableCell>{book.title}</StockTableCell>
              <StockTableCell>{book.totalStock}</StockTableCell>
              <StockTableCell>
                <Box display="flex" gap="8px">
                  <EditButton variant="contained" onClick={() => handleEdit(book)}>
                    Edit
                  </EditButton>
                  <DeleteButton variant="contained" onClick={() => handleDelete(book.id)}>
                    Hapus
                  </DeleteButton>
                </Box>
              </StockTableCell>
            </StockTableRow>
          ))}
        </tbody>
      </StockTable>
      {selectedBook && (
        <EditBookStockDialog
          book={selectedBook}
          open={isDialogOpen}
          onClose={handleDialogClose}
          onSaveSuccess={handleSaveSuccess} // Pass the success handler to the dialog
        />
      )}
      <Snackbar
        open={isSnackbarOpen}
        autoHideDuration={6000}
        onClose={handleSnackbarClose}
        message={`Stok buku "${selectedBook?.title}" telah diperbarui!`}
        anchorOrigin={{ vertical: 'top', horizontal: 'right' }} // Position the snackbar in the top right
        action={
          <Button color="inherit" size="small" onClick={handleSnackbarClose}>
            Oke
          </Button>
        }
      />
    </Container>
  );
};

export default BooksStock;
