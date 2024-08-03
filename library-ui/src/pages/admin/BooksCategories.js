// src/pages/CategoriesData.js
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

const Categories = () => {
    const [categories, setCategories] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        const fetchCategories = async () => {
            try {
                const response = await api.get('/api/categories');
                setCategories(response.data);
            } catch (error) {
                console.error('Error fetching categories:', error);
            }
        };

        fetchCategories();
    }, []);

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredCategories = categories.filter((category) =>
        category.name.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const handleEdit = (categoryId) => {
        // Implement edit functionality
        console.log('Edit category', categoryId);
    };

    const handleDelete = (categoryId) => {
        // Implement delete functionality
        console.log('Delete category', categoryId);
    };

    const handleAdd = () => {
        // Implement add functionality
        console.log('Add category');
    };

    return (
        <Container maxWidth="lg">
            <Typography variant="h4" gutterBottom>
                Data Kategori
            </Typography>
            <Box display="flex" justifyContent="space-between" alignItems="center" marginBottom="16px">
                <TextField
                    variant="outlined"
                    placeholder="cari berdasarkan nama kategori"
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
                        Tambah Kategori
                    </AddButton>
                </Box>
            </Box>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>ID</TableCell>
                        <TableCell>Kategori</TableCell>
                        <TableCell align="right">Aksi</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {filteredCategories.map((category) => (
                        <TableRow key={category.id}>
                            <TableCell>{category.id}</TableCell>
                            <TableCell>{category.name}</TableCell>
                            <TableCell align="right">
                                <Box display="flex" justifyContent="flex-end" gap="8px">
                                    <EditButton variant="contained" onClick={() => handleEdit(category.id)}>
                                        Edit
                                    </EditButton>
                                    <DeleteButton variant="contained" onClick={() => handleDelete(category.id)}>
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

export default Categories;