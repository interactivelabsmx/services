package com.il.sod.db.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Asset database table.
 * 
 */
@Entity
@NamedQuery(name="Asset.findAll", query="SELECT a FROM Asset a")
public class Asset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idAsset;

	private String description;

	private String name;

	private int status;

	//bi-directional many-to-one association to AssetType
	@ManyToOne
	@JoinColumn(name="idAssetType")
	private AssetType assetType;

	//bi-directional many-to-one association to AssetTaskOrder
	@OneToMany(mappedBy="asset")
	private List<AssetTaskOrder> assetTaskOrders;

	//bi-directional many-to-one association to AssetTaskService
	@OneToMany(mappedBy="asset")
	private List<AssetTaskService> assetTaskServices;

	public Asset() {
	}

	public int getIdAsset() {
		return this.idAsset;
	}

	public void setIdAsset(int idAsset) {
		this.idAsset = idAsset;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public AssetType getAssetType() {
		return this.assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public List<AssetTaskOrder> getAssetTaskOrders() {
		return this.assetTaskOrders;
	}

	public void setAssetTaskOrders(List<AssetTaskOrder> assetTaskOrders) {
		this.assetTaskOrders = assetTaskOrders;
	}

	public AssetTaskOrder addAssetTaskOrder(AssetTaskOrder assetTaskOrder) {
		getAssetTaskOrders().add(assetTaskOrder);
		assetTaskOrder.setAsset(this);

		return assetTaskOrder;
	}

	public AssetTaskOrder removeAssetTaskOrder(AssetTaskOrder assetTaskOrder) {
		getAssetTaskOrders().remove(assetTaskOrder);
		assetTaskOrder.setAsset(null);

		return assetTaskOrder;
	}

	public List<AssetTaskService> getAssetTaskServices() {
		return this.assetTaskServices;
	}

	public void setAssetTaskServices(List<AssetTaskService> assetTaskServices) {
		this.assetTaskServices = assetTaskServices;
	}

	public AssetTaskService addAssetTaskService(AssetTaskService assetTaskService) {
		getAssetTaskServices().add(assetTaskService);
		assetTaskService.setAsset(this);

		return assetTaskService;
	}

	public AssetTaskService removeAssetTaskService(AssetTaskService assetTaskService) {
		getAssetTaskServices().remove(assetTaskService);
		assetTaskService.setAsset(null);

		return assetTaskService;
	}

}