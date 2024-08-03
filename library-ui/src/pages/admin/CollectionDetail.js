import React, { useState, useEffect } from 'react';
import { Container, Paper, Typography, Button, Box, Grid, Snackbar, Alert } from '@mui/material';
import { styled } from '@mui/material/styles';
import { useParams, useNavigate, useLocation } from 'react-router-dom';
import api from '../../utility/api';
import defaultAvatar from '../../assets/rabbit-mammal-animals.png';

const BookImage = styled('img')({
    minWidth: '150px',
    width: '100%',
    minHeight: '150px',
    height: 'auto',
    objectFit: 'cover',
    borderRadius: '8px',
});

const DetailItem = styled(Typography)({
    marginBottom: '8px',
});

const Synopsis = styled(Typography)({
    whiteSpace: 'normal',
    wordWrap: 'break-word',
});

const CollectionDetail = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const { id: bookId } = useParams();
    const [book, setBook] = useState({
        id: '',
        title: '',
        author: '',
        publisher: '',
        category: '',
        year: '',
        availableCopies: '',
        language: '',
        isbn: '',
        pages: '',
        position: '',
        synopsis: '',
        imageUrl: '',
    });
    const [snackbarOpen, setSnackbarOpen] = useState(false);

    useEffect(() => {
        const fetchBookDetail = async () => {
            try {
                if (location.state?.showSnackbar) {
                    setSnackbarOpen(true);
                }
                const response = await api.get(`/api/books/${bookId}`);
                setBook(response.data);
            } catch (error) {
                console.error('Error fetching book detail:', error);
            }
        };

        fetchBookDetail();
    }, [bookId, location.state]);

    const handleSnackbarClose = () => {
        setSnackbarOpen(false);
    };

    console.log(`Collection Detail : ${book}`);

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
            <Box component={Paper} p={4} mt={4} boxShadow={3}>
                <Typography variant="h4" gutterBottom>
                    Detail Koleksi
                </Typography>
                <Grid container spacing={3}>
                    <Grid item xs={12} sm={4}>
                        <BookImage src={book.imageUrl || defaultAvatar} alt={book.title} />
                    </Grid>
                    <Grid item xs={12} sm={8}>
                        <Typography variant="h4" color="primary" gutterBottom>
                            {book.title || 'Judul buku xxxxxxxxxxxx'}
                        </Typography>
                        <Grid container spacing={2}>
                            <Grid item xs={12} sm={6}>
                                <DetailItem variant="body1">Penulis: {book.author || 'xxxxxxxx'}</DetailItem>
                                <DetailItem variant="body1">Penerbit: {book.publisher || 'xxxxxxxx'}</DetailItem>
                                <DetailItem variant="body1">Kategori: {book.category || 'xxxxxxxx'}</DetailItem>
                                <DetailItem variant="body1">Tahun Terbit: {book.year || 'xxxx'}</DetailItem>
                                <DetailItem variant="body1">Tersedia untuk di pinjam: {book.availableCopies || 'xx'}</DetailItem>
                            </Grid>
                            <Grid item xs={12} sm={6}>
                                <DetailItem variant="body1">Bahasa: {book.language || 'xxxxxxxx'}</DetailItem>
                                <DetailItem variant="body1">ISBN: {book.isbn || 'xxxxxxxx'}</DetailItem>
                                <DetailItem variant="body1">Jumlah halaman: {book.pages || 'xxxxxxxx'}</DetailItem>
                                <DetailItem variant="body1">Posisi: {book.position || 'xxxxxxxx'}</DetailItem>
                            </Grid>
                        </Grid>
                    </Grid>
                </Grid>
                <Box mt={3}>
                    <Typography variant="h5" gutterBottom>
                        Sinopsis
                    </Typography>
                    <Synopsis variant="body1">
                        {book.synopsis || 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.'}
                    </Synopsis>
                </Box>
                <Box mt={3} display="flex" justifyContent="space-between" width="100%">
                    <Button variant="contained" size="large" sx={{ mt: 2 }} onClick={() => navigate('/admin/collection')}>
                        <Typography variant="button">Back</Typography>
                    </Button>
                    <Button variant="contained" color="secondary" size="large" sx={{ mt: 2 }} onClick={() => navigate(`/admin/collection/edit/${book.id}`, { state: { from: location } })}>
                        <Typography variant="button">Edit</Typography>
                    </Button>
                </Box>
            </Box>
        </Container>
    );
};

export default CollectionDetail;
