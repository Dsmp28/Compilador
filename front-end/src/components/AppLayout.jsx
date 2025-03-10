import Sidebar from './Sidebar.jsx';
import { Outlet } from 'react-router-dom';

const AppLayout = () => {
    return (
        <div className="app-layout flex">
        <Sidebar />
        <div className="content">
            <Outlet />
        </div>
        </div>
    );
}

export default AppLayout;