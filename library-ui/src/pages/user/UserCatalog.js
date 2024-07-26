// src/pages/UserCatalog.js
import React, { useState, useEffect } from 'react';
import { Container, Grid, Paper, Typography, Button, TextField, InputAdornment, IconButton, Box } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import { styled } from '@mui/material/styles';
import { useNavigate } from 'react-router-dom';
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

const UserCatalog = () => {
    const [books, setBooks] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchBooks = async () => {
            try {
                const response = await api.get('/api/books');
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

    return (
        <Container maxWidth="lg">
            <Typography variant="h4" gutterBottom>
                Katalog Online
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
            <Grid container spacing={3}>
                {filteredBooks.map((book, index) => (
                    <Grid item xs={12} key={index}>
                        <BookItem>
                            <BookImage src={book.imageUrl || 'placeholder-image-url'} alt={book.title} />
                            <BookDetails>
                                <Typography variant="h6">{book.title}</Typography>
                                <Typography variant="body1">Penulis: {book.author}</Typography>
                                <Typography variant="body1">Kategori: {book.category}</Typography>
                                <Typography variant="body1">Bahasa: {book.language}</Typography>
                                <Typography variant="body1">Posisi: {book.position}</Typography>
                                <Typography variant="body1">Tersedia untuk di pinjam: {book.availableCopies}</Typography>
                            </BookDetails>
                            <Box display="flex" flexDirection="column">
                                <Button variant="contained" color="primary" style={{ marginBottom: '8px' }} onClick={() => { navigate(`/user/catalog/detail/${book.id}`) }}>
                                    Lihat Detail
                                </Button>
                                <Button variant="contained" color="secondary">
                                    Pesan
                                </Button>
                            </Box>
                        </BookItem>
                    </Grid>
                ))}
            </Grid>
        </Container>
    );
};

export default UserCatalog;