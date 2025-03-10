import ProjectList from "./ProjectList.jsx"
const SidebarProjects = ( {projects} ) => {
    return (
        <aside className="sidebarProjects">
            <h2>Tus proyectos</h2>
            <ProjectList projects={projects} />
        </aside>
    );
}