// src/pages/AuthorsData.js
import React, { useState, useEffect } from 'react';
import { Container, Typography, TextField, InputAdornment, IconButton, Box, Button, Table, TableHead, TableBody, TableRow, TableCell } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import AddIcon from '@mui/icons-material/Add';
import { styled } from '@mui/material/styles';
import api from '../../utility/api';

const EditButton = styled(Button)({
    backgroundColor: '#A1887F',
    color: 'white',
    '&:hover': {
        backgroundColor: '#8D6E63',
    },
});

const DeleteButton = styled(Button)({
    backgroundColor: '#F44336',
    color: 'white',
    '&:hover': {
        backgroundColor: '#E53935',
    },
});

const AddButton = styled(Button)({
    backgroundColor: '#FFA726',
    color: 'white',
    '&:hover': {
        backgroundColor: '#FB8C00',
    },
});

const Authors = () => {
    const [authors, setAuthors] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        const fetchAuthors = async () => {
            try {
                const response = await api.get('/api/authors');
                setAuthors(response.data);
            } catch (error) {
                console.error('Error fetching authors:', error);
            }
        };

        fetchAuthors();
    }, []);

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredAuthors = authors.filter((author) =>
        author.name.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const handleEdit = (authorId) => {
        // Implement edit functionality
        console.log('Edit author', authorId);
    };

    const handleDelete = (authorId) => {
        // Implement delete functionality
        console.log('Delete author', authorId);
    };

    const handleAdd = () => {
        // Implement add functionality
        console.log('Add author');
    };

    return (
        <Container maxWidth="lg">
            <Typography variant="h4" gutterBottom>
                Data Penulis
            </Typography>
            <Box display="flex" justifyContent="space-between" alignItems="center" marginBottom="16px">
                <TextField
                    variant="outlined"
                    placeholder="cari berdasarkan nama penulis"
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
                <AddButton variant="contained" onClick={handleAdd} startIcon={<AddIcon />}>
                    Tambah Rak
                </AddButton>
            </Box>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>ID</TableCell>
                        <TableCell>Nama Penulis</TableCell>
                        <TableCell align="right">Aksi</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {filteredAuthors.map((author) => (
                        <TableRow key={author.id}>
                            <TableCell>{author.id}</TableCell>
                            <TableCell>{author.name}</TableCell>
                            <TableCell align="right">
                                <Box display="flex" justifyContent="flex-end" gap="8px">
                                    <EditButton variant="contained" onClick={() => handleEdit(author.id)}>
                                        Edit
                                    </EditButton>
                                    <DeleteButton variant="contained" onClick={() => handleDelete(author.id)}>
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

export default Authors;