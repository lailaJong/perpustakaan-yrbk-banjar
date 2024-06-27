import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Container, Grid, Paper, Typography } from '@mui/material';
import StatCard from '../components/StatCard';
import BookList from '../components/BookList';
import BorrowerList from '../components/BorrowerList';
import OrderList from '../components/OrderList';

const Beranda = () => {
    const [stats, setStats] = useState({});
    const [popularBooks, setPopularBooks] = useState([]);
    const [topBorrowers, setTopBorrowers] = useState([]);
    const [recentOrders, setRecentOrders] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const statsResponse = await axios.get('/api/stats');
                const popularBooksResponse = await axios.get('/api/popular-books');
                const topBorrowersResponse = await axios.get('/api/top-borrowers');
                const recentOrdersResponse = await axios.get('/api/recent-orders');

                setStats(statsResponse.data);
                setPopularBooks(popularBooksResponse.data);
                setTopBorrowers(topBorrowersResponse.data);
                setRecentOrders(recentOrdersResponse.data);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, []);

    return (
        <Container maxWidth="lg">
            <Typography variant="h4" gutterBottom>
                Dashboard
            </Typography>
            <Grid container spacing={3}>
                <Grid item xs={12} md={6} lg={3}>
                    <StatCard title="Total Koleksi" count={stats.totalCollection} />
                </Grid>
                <Grid item xs={12} md={6} lg={3}>
                    <StatCard title="Total Pemustaka" count={stats.totalMembers} />
                </Grid>
                <Grid item xs={12} md={6} lg={3}>
                    <StatCard title="Total Transaksi Peminjaman" count={stats.totalLoanTransactions} />
                </Grid>
                <Grid item xs={12} md={6} lg={3}>
                    <StatCard title="Total Buku Telat Kembali" count={stats.totalOverdueBooks} />
                </Grid>
                <Grid item xs={12} md={6}>
                    <Paper>
                        <BookList title="Daftar Buku Paling Diminati" books={popularBooks} />
                    </Paper>
                </Grid>
                <Grid item xs={12} md={6}>
                    <Paper>
                        <BorrowerList title="Daftar Peminjam Buku Teratas" borrowers={topBorrowers} />
                    </Paper>
                </Grid>
                <Grid item xs={12}>
                    <Paper>
                        <OrderList title="Daftar Pesanan Buku Terkini" orders={recentOrders} />
                    </Paper>
                </Grid>
            </Grid>
        </Container>
    );
};

export default Beranda;
