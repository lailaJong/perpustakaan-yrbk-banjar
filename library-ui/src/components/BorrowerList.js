import React from 'react';
import { List, ListItem, ListItemText, Typography } from '@mui/material';

const BorrowerList = ({ title, borrowers }) => {
    return (
        <div>
            <Typography variant='h6' sx={{ backgroundColor: '#D97706', padding: '8px' }}>{title}</Typography>
            <List>
                {borrowers.map((borrower, index) => (
                    <ListItem key={index}>
                        <ListItemText primary={borrower.name} secondary={borrower.count} />
                    </ListItem>
                ))}
            </List>
        </div>
    );
};

export default BorrowerList;