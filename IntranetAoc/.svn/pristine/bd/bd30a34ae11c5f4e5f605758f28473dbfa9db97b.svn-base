package com.land.front.beans;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.organigram.OrganigramHelper;
import org.primefaces.event.organigram.OrganigramNodeCollapseEvent;
import org.primefaces.event.organigram.OrganigramNodeExpandEvent;
import org.primefaces.event.organigram.OrganigramNodeSelectEvent;
import org.primefaces.model.DefaultOrganigramNode;
import org.primefaces.model.OrganigramNode;

@ViewScoped
@ManagedBean
public class BeanOrganigrama {

	private OrganigramNode rootNode;
	private OrganigramNode selection;

	private boolean zoom = true;
	

	private String employeeName;

	@PostConstruct
	public void init() {
		selection = new DefaultOrganigramNode(null, "Ridvan Agar", null);

		rootNode = new DefaultOrganigramNode("root", "General Manager Julio Souto (2)", null);
		rootNode.setCollapsible(false);
		rootNode.setDroppable(true);

		OrganigramNode softwareDevelopment = addDivision(rootNode, "Human Resources Manager Jorge Nicolás (1)", "Front Desk Mariana Marroquín (4)");

		
		


		addDivision(rootNode, "ControllerMario Arana (1)", "A/R Manager Axel Fernando Medina (1)", "Accountant Senior Ramiro Cruz (1)", "Accountant Intern Erick Cabrera (1)");

		OrganigramNode marketing = addDivision(rootNode, "Revenue Manager Mauro Custodio (3)");
		addDivision(marketing, "Sales Representative Carlos Avila (4)");
		

		addDivision(rootNode, "Service Manager Vacante (4)", "Service Senior María Jacinto (4)");
		
		addDivision(rootNode, "Logistics Manager Vacante (4)", "Logistics Senior Cesar Caudillo (1)");
	}

	protected OrganigramNode addDivision(OrganigramNode parent, String name, String... employees) {
		OrganigramNode divisionNode = new DefaultOrganigramNode("division", name, parent);
		divisionNode.setDroppable(true);
		divisionNode.setDraggable(true);
		divisionNode.setSelectable(true);

		if (employees != null) {
			for (String employee : employees) {
				OrganigramNode employeeNode = new DefaultOrganigramNode("employee", employee, divisionNode);
				employeeNode.setDraggable(true);
				employeeNode.setSelectable(true);
			}
		}

		return divisionNode;
	}

	//public void nodeDragDropListener(OrganigramNodeDragDropEvent event) {
		//FacesMessage message = new FacesMessage();
		//message.setSummary("Node '" + event.getOrganigramNode().getData() + "' moved from "
			//	+ event.getSourceOrganigramNode().getData() + " to '" + event.getTargetOrganigramNode().getData()
				//+ "'");
	//	message.setSeverity(FacesMessage.SEVERITY_INFO);

		//FacesContext.getCurrentInstance().addMessage(null, message);
	//}

	public void nodeSelectListener(OrganigramNodeSelectEvent event) {
		FacesMessage message = new FacesMessage();
		message.setSummary("Node '" + event.getOrganigramNode().getData() + "' selected.");
		message.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void nodeCollapseListener(OrganigramNodeCollapseEvent event) {
		FacesMessage message = new FacesMessage();
		message.setSummary("Node '" + event.getOrganigramNode().getData() + "' collapsed.");
		message.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void nodeExpandListener(OrganigramNodeExpandEvent event) {
		FacesMessage message = new FacesMessage();
		message.setSummary("Node '" + event.getOrganigramNode().getData() + "' expanded.");
		message.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void removeDivision() {
		// re-evaluate selection - might be a differenct object instance if
		// viewstate serialization is enabled
		OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
		setMessage(currentSelection.getData() + " will entfernt werden.", FacesMessage.SEVERITY_INFO);
	}

	public void removeEmployee() {
		// re-evaluate selection - might be a differenct object instance if
		// viewstate serialization is enabled
		OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
		currentSelection.getParent().getChildren().remove(currentSelection);
	}

	public void addEmployee() {
		// re-evaluate selection - might be a differenct object instance if
		// viewstate serialization is enabled
		OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);

		OrganigramNode employee = new DefaultOrganigramNode("employee", employeeName, currentSelection);
		employee.setDraggable(true);
		employee.setSelectable(true);
	}

	private void setMessage(String msg, FacesMessage.Severity severity) {
		FacesMessage message = new FacesMessage();
		message.setSummary(msg);
		message.setSeverity(severity);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public OrganigramNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(OrganigramNode rootNode) {
		this.rootNode = rootNode;
	}

	public OrganigramNode getSelection() {
		return selection;
	}

	public void setSelection(OrganigramNode selection) {
		this.selection = selection;
	}

	public boolean isZoom() {
		return zoom;
	}

	public void setZoom(boolean zoom) {
		this.zoom = zoom;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	
}
