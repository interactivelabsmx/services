package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.Subproduct;
import com.il.sod.db.model.repositories.SubproductRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.SubproductMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.SubproductDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RolesAllowed("ADMIN")
@Path("/subproduct")
@Produces(MediaType.APPLICATION_JSON)
 @Api(value = "/subproduct", tags = { "subproduct" })
public class SubproductService extends AbstractServiceMutations {

	@Autowired
	SubproductRepository subproductRepository;

	@POST
	@ApiOperation(value = "Create Subproduct", response = SubproductDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveSubproduct(SubproductDTO dto) throws SODAPIException {
		try {
			Subproduct entity = SubproductMapper.INSTANCE.map(dto);
			this.saveEntity(subproductRepository, entity);
			dto = SubproductMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Subproduct", response = SubproductDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateSubproduct(SubproductDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Subproduct", response = SubproductDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateSubproductById(@PathParam("id") String id, SubproductDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}


	private Response updateEntity(SubproductDTO dto) throws SODAPIException {
		try {
			Subproduct entity = SubproductMapper.INSTANCE.map(dto);
			this.updateEntity(subproductRepository, entity);
			dto = SubproductMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Subproduct", response = SubproductDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteSubproduct(SubproductDTO dto) throws SODAPIException {
		try {
			Subproduct entity = SubproductMapper.INSTANCE.map(dto);
			this.deleteEntity(subproductRepository, entity.getIdSubproduct());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Entity deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Subproduct list", response = SubproductDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getSubproductList() throws SODAPIException {
		List<Subproduct> rentityList = this.getEntityList(subproductRepository);
		List<SubproductDTO> list = rentityList.stream().map((i) -> {
			SubproductDTO dto = SubproductMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
