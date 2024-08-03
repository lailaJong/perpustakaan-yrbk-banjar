import React from 'react';
import { Dialog, DialogTitle, DialogContent, DialogActions, Button, Typography, Box } from '@mui/material';
import WarningIcon from '@mui/icons-material/Warning';
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

const EditLateReturnStatusDialog = ({ open, onClose, onSave, transaction }) => {
  return (
    <Dialog
      open={open}
      onClose={onClose}
      fullWidth
      maxWidth="sm"
    >
      <DialogTitle>
        <Box display="flex" alignItems="center">
          <WarningIcon style={{ marginRight: 8, color: 'primary' }} />
          <Typography variant="h6" style={{ color: 'primary' }}>
            Peringatan
          </Typography>
        </Box>
      </DialogTitle>
      <DialogContent dividers>
        <Typography variant="body1">
          Apakah anda yakin bahwa transaksi peminjaman dengan id : {transaction.id} dinyatakan {transaction.status}?
        </Typography>
      </DialogContent>
      <DialogActions>
        <CancelButton onClick={onClose}>Tidak</CancelButton>
        <EditButton onClick={onSave}>Ya</EditButton>
      </DialogActions>
    </Dialog>
  );
};

export default EditLateReturnStatusDialog;
