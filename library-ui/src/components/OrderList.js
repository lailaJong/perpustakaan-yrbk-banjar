import React from 'react';
import { List, ListItem, ListItemText, Typography, Button } from '@mui/material';

const OrderList = ({ title, orders }) => {
  return (
    <div>
      <Typography variant="h6" sx={{ backgroundColor: '#D97706', padding: '8px' }}>{title}</Typography>
      <List>
        {orders.map((order, index) => (
          <ListItem key={index} sx={{ display: 'flex', justifyContent: 'space-between' }}>
            <ListItemText primary={order.name} />
            <div>
              <Button variant="contained" color="success" sx={{ marginRight: '8px' }}>Proses</Button>
              <Button variant="contained" color="error">Batalkan</Button>
            </div>
          </ListItem>
        ))}
      </List>
    </div>
  );
};

export default OrderList;
