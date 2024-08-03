import React, { useState } from 'react';
import { Container, Paper, Typography, Button, Box, Grid, TextField, MenuItem } from '@mui/material';
import { styled } from '@mui/material/styles';
import { useNavigate } from 'react-router-dom';

const Header = styled(Box)(({ theme }) => ({
    backgroundColor: theme.palette.background.header,
    fontFamily: 'Kanit, sans-serif',
    padding: '16px',
    marginBottom: '24px',
    borderRadius: '4px',
    boxShadow: theme.shadows[1],
}));

const AddBook = () => {
    const navigate = useNavigate();
    const [book, setBook] = useState({
        title: '',
        category: '',
        author: '',
        publisher: '',
        year: '',
        isbn: '',
        pages: '',
        stock: '',
        synopsis: '',
        shelf: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setBook({ ...book, [name]: value });
    };

    const handleSave = () => {
        // Save logic here
    };

    return (
        <Container maxWidth="lg">
            <Box component={Paper} p={4} mt={4} boxShadow={3}>
                <Header>
                    <Typography variant="h4">Tambah Koleksi</Typography>
                </Header>
                <Grid container spacing={3}>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            fullWidth
                            label="Judul Buku"
                            name="title"
                            value={book.title}
                            onChange={handleChange}
                            margin="normal"
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            fullWidth
                            label="ISBN"
                            name="isbn"
                            value={book.isbn}
                            onChange={handleChange}
                            margin="normal"
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            fullWidth
                            label="Kategori"
                            name="category"
                            select
                            value={book.category}
                            onChange={handleChange}
                            margin="normal"
                        >
                            {/* Add category options here */}
                            <MenuItem value="Fiction">Fiction</MenuItem>
                            <MenuItem value="Non-Fiction">Non-Fiction</MenuItem>
                        </TextField>
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            fullWidth
                            label="Tahun Terbit"
                            name="year"
                            value={book.year}
                            onChange={handleChange}
                            margin="normal"
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            fullWidth
                            label="Penerbit"
                            name="publisher"
                            value={book.publisher}
                            onChange={handleChange}
                            margin="normal"
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            fullWidth
                            label="Jumlah Halaman"
                            name="pages"
                            value={book.pages}
                            onChange={handleChange}
                            margin="normal"
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            fullWidth
                            label="Penulis"
                            name="author"
                            value={book.author}
                            onChange={handleChange}
                            margin="normal"
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            fullWidth
                            label="Stock"
                            name="stock"
                            value={book.stock}
                            onChange={handleChange}
                            margin="normal"
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            fullWidth
                            label="Rak"
                            name="shelf"
                            value={book.shelf}
                            onChange={handleChange}
                            margin="normal"
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            fullWidth
                            multiline
                            rows={4}
                            label="Sinopsis"
                            name="synopsis"
                            value={book.synopsis}
                            onChange={handleChange}
                            margin="normal"
                        />
                    </Grid>
                </Grid>
                <Box mt={3} display="flex" justifyContent="space-between" width="100%">
                    <Button variant="contained" size="large" sx={{ mt: 2 }} onClick={() => navigate(-1)} color="secondary">
                        <Typography variant="button">Batal</Typography>
                    </Button>
                    <Button variant="contained" size="large" sx={{ mt: 2 }} onClick={handleSave} color="primary">
                        <Typography variant="button">Simpan</Typography>
                    </Button>
                </Box>
            </Box>
        </Container>
    );
};

export default AddBook;
