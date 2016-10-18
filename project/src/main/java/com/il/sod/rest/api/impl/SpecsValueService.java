package com.il.sod.rest.api.impl;

import com.il.sod.db.dao.impl.SpecsValueDAO;
import com.il.sod.db.model.entities.SpecsValue;
import com.il.sod.db.model.repositories.SpecRepository;
import com.il.sod.db.model.repositories.SpecsValueRepository;
import com.il.sod.db.model.repositories.SupplyTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.SpecsMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.SpecsValueDTO;
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
@Path("/specs-value")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/specs-value", tags = { "specs" })
public class SpecsValueService extends AbstractServiceMutations {
	
	@Autowired
	SpecsValueRepository specsValueRepository;

	@Autowired
	SpecRepository specRepository;
	
	@Autowired
	SupplyTypeRepository supplyTypeRepository;
	
	@Autowired
	private SpecsValueDAO specsValueDAO;

	@POST
	@ApiOperation(value = "Create SpecsValue", response = SpecsValueDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveSpecsValue(SpecsValueDTO dto) throws SODAPIException {
		try {
			SpecsValue entity = SpecsMapper.INSTANCE.map(dto);
			if (entity.getIdSupplyType() > 0 && specsValueRepository.findByTypeSupply(entity.getSpec().getId(), entity.getIdSupplyType()).size() > 0 ){
				throw new SODAPIException(Response.Status.BAD_REQUEST, " A SpecValue for that supply type already exist. ");
			}
			
			this.saveEntity(specsValueRepository, entity);
			dto = SpecsMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update SpecsValue", response = SpecsValueDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateSpecsValue(SpecsValueDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(SpecsValueDTO dto) throws SODAPIException {
		try {
			SpecsValue entity = SpecsMapper.INSTANCE.map(dto);
			if (entity.getIdSupplyType() > 0 && specsValueRepository.findByTypeSupplyDifferent(entity.getSpec().getId(), entity.getIdSupplyType(), entity.getId()).size() > 0 ){
				throw new SODAPIException(Response.Status.BAD_REQUEST, " A SpecValue for that supply type already exist. ");
			}
			this.updateEntity(specsValueRepository, entity);
			dto = SpecsMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update SpecsValue", response = SpecsValueDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateSpecsValueById(@PathParam("id") String id, SpecsValueDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete", response = GeneralResponseMessage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		SpecsValue entity = specsValueRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(specsValueRepository, entity.getId());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Item deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get SpecsValue list", response = SpecsValueDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getSpecsValueList() throws SODAPIException {
		SpecsMapper.INSTANCE.setSupplyTypeRepository(supplyTypeRepository);
		List<SpecsValue> entityList = this.getEntityList(specsValueRepository);
		List<SpecsValueDTO> list = entityList.stream().map((i) -> {
			SpecsValueDTO dto = SpecsMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}
	
	@GET
	@Path("/byIdSpec/{idSpecs}")
	@ApiOperation(value = "Get Specs Value list by idSpec", response = SpecsValueDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getSpecsValuesById(@PathParam("idSpecs") String idSpecs) throws SODAPIException {
		SpecsMapper.INSTANCE.setSupplyTypeRepository(supplyTypeRepository);
		List<SpecsValue> entityList = specsValueDAO.findBySpec(Integer.valueOf(idSpecs));
		List<SpecsValueDTO> list = entityList.stream().map((i) -> {
			SpecsValueDTO dto = SpecsMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
