// src/pages/EditBookStockDialog.js
import React, { useState } from 'react';
import { Dialog, DialogTitle, DialogContent, DialogActions, TextField, Button, MenuItem, Typography, Box } from '@mui/material';
import { styled } from '@mui/system';

const EditButton = styled(Button)(({ theme }) => ({
  backgroundColor: theme.palette.primary.main,
  color: 'white',
  '&:hover': {
    backgroundColor: theme.palette.primary.dark,
  },
}));

const CancelButton = styled(Button)(({ theme }) => ({
  backgroundColor: theme.palette.secondary.main,
  color: 'white',
  '&:hover': {
    backgroundColor: theme.palette.secondary.dark,
  },
}));

const EditBookStockDialog = ({ book, open, onClose, onSaveSuccess }) => {
  const [totalStock, setTotalStock] = useState(book.totalStock);

  const handleStockChange = (event) => {
    setTotalStock(event.target.value);
  };

  const handleSave = () => {
    // Implement save functionality
    console.log('Save book stock', book.id, totalStock);
    onSaveSuccess();
  };

  return (
    <Dialog
      open={open}
      onClose={onClose}
      fullWidth
      maxWidth="md"
    >
      <DialogTitle>
        <Box display="flex" justifyContent="space-between" alignItems="center">
          <Typography variant="h6">
            {book.title || 'Edit Stok Buku'}
          </Typography>
          <Typography variant="subtitle1">
            {book.bookId || 'Book ID'}
          </Typography>
        </Box>
      </DialogTitle>
      <DialogContent>
        <TextField
          label="Jumlah Stok"
          select
          fullWidth
          value={totalStock}
          onChange={handleStockChange}
          margin="normal"
        >
          {[...Array(100).keys()].map((stock) => (
            <MenuItem key={stock + 1} value={stock + 1}>
              {stock + 1}
            </MenuItem>
          ))}
        </TextField>
      </DialogContent>
      <DialogActions>
        <CancelButton onClick={onClose}>Batal</CancelButton>
        <EditButton onClick={handleSave}>Simpan</EditButton>
      </DialogActions>
    </Dialog>
  );
};

export default EditBookStockDialog;
