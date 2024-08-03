import React from 'react';
import { Card, CardContent, Typography, Link } from '@mui/material';

const StatCard = ({ title, count, subInfo, detailLink }) => {
    return (
        <Card>
            <CardContent sx={{height: 'auto', minHeight: '180px'}}>
                <Typography variant='h6'>{title}</Typography>
                <Typography variant='h4'>{count}</Typography>
                <Typography variant='subtitle1'>{subInfo}</Typography>
                <Link href={detailLink} underline='always'>Lihat Detail</Link>
            </CardContent>
        </Card>
    );
};

export default StatCard;
