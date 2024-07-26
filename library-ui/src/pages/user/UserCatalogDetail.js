import React, { useState, useEffect } from 'react';
import { Container, Paper, Typography, Button, Box, Grid } from '@mui/material';
import { styled } from '@mui/material/styles';
import { useParams, useNavigate } from 'react-router-dom';
import api from '../../utility/api';

const BookImage = styled('img')({
    width: '150px',
    height: '200px',
    objectFit: 'cover',
    borderRadius: '8px',
});

const DetailItem = styled(Typography)({
    marginBottom: '8px',
});

const Header = styled(Box)(({ theme }) => ({
    backgroundColor: theme.backgrounds.header,
    fontFamily: 'Kanit, sans-serif',
    padding: '16px',
    marginBottom: '24px',
    borderRadius: '4px',
    boxShadow: theme.shadows[1],
}));

const UserCatalogDetail = () => {
    const navigate = useNavigate();
    const { id: bookId } = useParams();
    const [book, setBook] = useState({
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

    useEffect(() => {
        const fetchBookDetail = async () => {
            try {
                const response = await api.get(`/api/books/${bookId}`);
                setBook(response.data);
            } catch (error) {
                console.error('Error fetching book detail:', error);
            }
        };

        fetchBookDetail();
    }, [bookId]);

    return (
        <Container maxWidth="lg">
            <Header>
                <Typography variant="h4" gutterBottom>
                    Katalog Online
                </Typography>
            </Header>
            <Box component={Paper} p={4} mt={4} boxShadow={3}>
                <Typography variant="h4" gutterBottom>
                    Katalog Buku
                </Typography>
                <Grid container spacing={3}>
                    <Grid item xs={4}>
                        <BookImage src={book.imageUrl || 'placeholder-image-url'} alt={book.title} />
                    </Grid>
                    <Grid item xs={8}>
                        <Typography variant="h4" color="primary" gutterBottom>
                            {book.title || 'Judul buku xxxxxxxxxxxx'}
                        </Typography>
                        <Grid container spacing={2}>
                            <Grid item xs={6}>
                                <DetailItem variant="body1">Penulis: {book.author || 'xxxxxxxx'}</DetailItem>
                                <DetailItem variant="body1">Penerbit: {book.publisher || 'xxxxxxxx'}</DetailItem>
                                <DetailItem variant="body1">Kategori: {book.category || 'xxxxxxxx'}</DetailItem>
                                <DetailItem variant="body1">Tahun Terbit: {book.year || 'xxxx'}</DetailItem>
                                <DetailItem variant="body1">Tersedia untuk di pinjam: {book.availableCopies || 'xx'}</DetailItem>
                            </Grid>
                            <Grid item xs={6}>
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
                    <Typography variant="body1" whiteSpace="pre-wrap">
                        {book.synopsis || 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'}
                    </Typography>
                </Box>
                <Box mt={3} display="flex" justifyContent="space-between" width="100%">
                    <Button variant="contained" size="large" sx={{ mt: 2 }}>
                        <Typography variant="button">Back</Typography>
                    </Button>
                    <Button variant="contained" color="secondary" size="large" sx={{ mt: 2 }} onClick={() => { navigate('/user/catalog') }}>
                        <Typography variant="button">Pesan</Typography>
                    </Button>
                </Box>
            </Box>
        </Container>
    );
};

export default UserCatalogDetail;
