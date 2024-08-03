import React, { useState, useEffect } from 'react';
import { Container, Grid, Paper, Typography, Button, TextField, InputAdornment, IconButton, Box, Snackbar, Alert } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import AddIcon from '@mui/icons-material/Add';
import { styled } from '@mui/material/styles';
import { useNavigate, useLocation } from 'react-router-dom';
import api from '../../utility/api';

const BookImage = styled('img')({
    width: '100px',
    height: '150px',
    objectFit: 'cover',
});

const BookItem = styled(Paper)({
    display: 'flex',
    alignItems: 'center',
    padding: '16px',
    marginBottom: '16px',
});

const BookDetails = styled(Box)({
    flexGrow: 1,
    marginLeft: '16px',
});

const AddButton = styled(Button)({
    backgroundColor: '#FFA726',
    color: 'white',
    '&:hover': {
        backgroundColor: '#FB8C00',
    },
});

const CollectionList = () => {
    const [books, setBooks] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {
        const fetchBooks = async () => {
            try {
                if (location.state?.showSnackbar) {
                    setSnackbarOpen(true);
                }
                const response = await api.get('/api/books');
                setBooks(response.data);
            } catch (error) {
                console.error('Error fetching books:', error);
            }
        };

        fetchBooks();
    }, [location]);

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredBooks = books.filter((book) =>
        book.title.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const handleAdd = () => {
        navigate('/admin/collection/add');
    };

    const handleSnackbarClose = () => {
        setSnackbarOpen(false);
    };


    return (
        <Container maxWidth="lg">
            <Snackbar
                open={snackbarOpen}
                autoHideDuration={6000}
                onClose={handleSnackbarClose}
                anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
            >
                <Alert onClose={handleSnackbarClose} severity="success" sx={{ width: '100%' }}>
                    Detail koleksi berhasil diperbarui!
                </Alert>
            </Snackbar>
            <Typography variant="h4" gutterBottom>
                Daftar Koleksi
            </Typography>
            <Box display="flex" justifyContent="space-between" alignItems="center" marginBottom="16px">
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
                <Box marginLeft="16px">
                    <AddButton variant="contained" onClick={handleAdd} startIcon={<AddIcon />}>
                        Tambah Koleksi
                    </AddButton>
                </Box>
            </Box>
            <Grid container spacing={3}>
                {filteredBooks.map((book, index) => (
                    <Grid item xs={12} key={index}>
                        <BookItem>
                            <BookImage src={book.imageUrl} alt={book.title} />
                            <BookDetails>
                                <Typography variant="h6">{book.title}</Typography>
                                <Typography variant="body1">Penulis: {book.author}</Typography>
                                <Typography variant="body1">Kategori: {book.category}</Typography>
                                <Typography variant="body1">Bahasa: {book.language}</Typography>
                                <Typography variant="body1">Posisi: {book.position}</Typography>
                                <Typography variant="body1">Tersedia untuk di pinjam: {book.availableCopies}</Typography>
                            </BookDetails>
                            <Box display="flex" flexDirection="column">
                                <Button variant="contained" color="primary" style={{ marginBottom: '8px' }} onClick={() => { navigate(`/admin/collection/detail/${book.id}`, { state: { from: location } }) }}>
                                    Lihat Detail
                                </Button>
                                <Button variant="contained" color="secondary" onClick={() => navigate(`/admin/collection/edit/${book.id}`)}>
                                    Edit
                                </Button>
                            </Box>
                        </BookItem>
                    </Grid>
                ))}
            </Grid>
        </Container>
    );
};

export default CollectionList;