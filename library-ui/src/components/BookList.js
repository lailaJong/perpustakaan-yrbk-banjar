import React from 'react';
import { List, ListItem, ListItemText, Typography } from '@mui/material';

const BookList = ({ title, books }) => {
    return (
        <div>
            <Typography variant="h6" sx={{ backgroundColor: '#D97706', padding: '8px' }}>{title}</Typography>
            <List>
                {books.map((book, index) => (
                    <ListItem key={index}>
                        <ListItemText primary={book.name} secondary={book.count} />
                    </ListItem>
                ))}
            </List>
        </div>
    );
};

export default BookList;
