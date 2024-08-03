// src/pages/AuthorsData.js
import React, { useState, useEffect } from 'react';
import { Container, Typography, TextField, InputAdornment, IconButton, Box, Button, Table, TableHead, TableBody, TableRow, TableCell } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import AddIcon from '@mui/icons-material/Add';
import { styled } from '@mui/material/styles';
import api from '../../utility/api';

const EditButton = styled(Button)(({ theme }) => ({
    backgroundColor: theme.palette.primary.main,
    color: 'white',
    '&:hover': {
        backgroundColor: '#8D6E63',
    },
}));

const DeleteButton = styled(Button)(({ theme }) => ({
    backgroundColor: theme.palette.tertiary.main,
    color: 'white',
    '&:hover': {
        backgroundColor: '#E53935',
    },
}));

const AddButton = styled(Button)({
    backgroundColor: '#FFA726',
    color: 'white',
    '&:hover': {
        backgroundColor: '#FB8C00',
    },
});

const Bookshelves = () => {
    const [bookshelves, setAuthors] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        const fetchAuthors = async () => {
            try {
                const response = await api.get('/api/bookshelves');
                setAuthors(response.data);
            } catch (error) {
                console.error('Error fetching bookshelves:', error);
            }
        };

        fetchAuthors();
    }, []);

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredAuthors = bookshelves.filter((bookshelf) =>
        bookshelf.code.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const handleEdit = (authorId) => {
        // Implement edit functionality
        console.log('Edit bookshelf', authorId);
    };

    const handleDelete = (authorId) => {
        // Implement delete functionality
        console.log('Delete bookshelf', authorId);
    };

    const handleAdd = () => {
        // Implement add functionality
        console.log('Add bookshelf');
    };

    return (
        <Container maxWidth="lg">
            <Typography variant="h4" gutterBottom>
                Penempatan Buku
            </Typography>
            <Box display="flex" justifyContent="space-between" alignItems="center" marginBottom="16px">
                <TextField
                    variant="outlined"
                    placeholder="cari berdasarkan nama rak buku"
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
                        Tambah Rak
                    </AddButton>
                </Box>
            </Box>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>ID</TableCell>
                        <TableCell>Kode Rak Buku</TableCell>
                        <TableCell align="right">Aksi</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {filteredAuthors.map((bookshelf) => (
                        <TableRow key={bookshelf.id}>
                            <TableCell>{bookshelf.id}</TableCell>
                            <TableCell>{bookshelf.code}</TableCell>
                            <TableCell align="right">
                                <Box display="flex" justifyContent="flex-end" gap="8px">
                                    <EditButton variant="contained" onClick={() => handleEdit(bookshelf.id)}>
                                        Edit
                                    </EditButton>
                                    <DeleteButton variant="contained" onClick={() => handleDelete(bookshelf.id)}>
                                        Hapus
                                    </DeleteButton>
                                </Box>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </Container>
    );
};

export default Bookshelves;