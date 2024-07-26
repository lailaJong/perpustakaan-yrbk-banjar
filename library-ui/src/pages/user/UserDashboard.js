// src/pages/UserBeranda.js
import React, { useState, useEffect } from 'react';
import api from '../../utility/api';
import { Container, Grid, Paper, Typography } from '@mui/material';
import StatCard from '../../components/StatCard';

const UserDashboard = () => {
    const [stats, setStats] = useState({});
    const [rules, setRules] = useState({})

    useEffect(() => {
        const fetchData = async () => {
            try {
                const statsResponse = await api.get('/api/user-stats');
                setStats(statsResponse.data);

                const rulesResponse = await api.get('/api/rules');
                setRules(rulesResponse.data);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, []);

    return (
        <Container maxWidth='lg'>
            <Typography variant='h4' gutterBottom>
                Keanggotaan Online
            </Typography>
            <Grid container spacing={3}>
                <Grid item xs={12} md={6} lg={3}>
                    <StatCard title='Status Anggota' count={stats.status || 'N/A'} subInfo={`Sejak ${stats.membershipStartDate || 'N/A'}`} detailLink='#' />
                </Grid>
                <Grid item xs={12} md={6} lg={3}>
                    <StatCard title='Point' count={stats.points || 'N/A'} detailLink='#' />
                </Grid>
                <Grid item xs={12} md={6} lg={3}>
                    <StatCard title='Jumlah Koleksi Dipinjam' count={stats.currentLoans || 'N/A'} detailLink='#' />
                </Grid>
                <Grid item xs={12} md={6} lg={3}>
                    <StatCard title='Jumlah Koleksi Dipesan' count={stats.reservedCollections || 'N/A'} detailLink='#' />
                </Grid>
                <Grid item xs={12} md={6} lg={3}>
                    <StatCard title='Total Riwayat Peminjaman' count={stats.totalLoanHistory || 'N/A'} detailLink='#' />
                </Grid>
                <Grid item xs={12}>
                    <Paper>
                        <Typography variant='h6' gutterBottom>
                            Skema Pemesanan
                        </Typography>
                        <Typography>
                            1. Pemesanan sebelum pukul 16:00: Koleksi diambil pada hari yang sama maksimal pukul 16.30.
                            Jika tidak diambil, pemesanan dibatalkan.
                        </Typography>
                        <Typography>
                            2. Pemesanan setelah pukul 16:00: Koleksi diambil hari berikutnya maksimal pukul 16.30.
                            Jika tidak diambil, pemesanan dibatalkan.
                        </Typography>
                    </Paper>
                </Grid>
                <Grid item xs={12}>
                    <Paper>
                        <Typography variant='h6' gutterBottom>
                            Skema Peminjaman
                        </Typography>
                        <Typography>
                            Kuota pinjam koleksi: {rules.loanQuota} koleksi. Lama durasi peminjaman: {rules.loanDuration} hari.
                        </Typography>
                    </Paper>
                </Grid>
                <Grid item xs={12}>
                    <Paper>
                        <Typography variant='h6' gutterBottom>
                            Info Benefit
                        </Typography>
                        <Typography>
                            Penambahan kuota pinjam koleksi: {stats.additionalLoanQuota} koleksi (total saat ini: {rules.loanQuota + stats.additionalLoanQuota} koleksi).
                            Penambahan lama durasi peminjaman: {stats.additionalLoanDuration} hari (total saat ini: {rules.loanDuration + stats.additionalLoanDuration} hari).
                        </Typography>
                    </Paper>
                </Grid>
            </Grid>
        </Container>
    );
};

export default UserDashboard;