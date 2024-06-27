import React from 'react';
import { Card, CardContent, Typography, Link } from '@mui/material';

const StatCard = ({ title, count }) => {
    return (
        <Card>
            <CardContent>
                <Typography variant="h6">{title}</Typography>
                <Typography variant="h4">{count}</Typography>
                <Link href="#" underline="always">Lihat Detail</Link>
            </CardContent>
        </Card>
    );
};

export default StatCard;
