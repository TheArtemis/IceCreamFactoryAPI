package com.example.springboot.controller;

import com.example.springboot.dto.IceCreamDTO;
import com.example.springboot.dto.IceCreamRequestDTO;
import com.example.springboot.dto.IceCreamsDTO;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.IceCream;
import com.example.springboot.service.IceCreamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5173")
@RequestMapping("/iceCream")
@Tag(name = "Ice Cream")
public class IceCreamController {
    @Autowired
    private IceCreamService iceCreamService;

    @Operation(
            description = "Get endpoint for Ice Cream",
            summary = "Use the Get endpoint to retrieve the best Ice Cream in the world",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "1"
                    ),
                    @ApiResponse(
                            description = "Missing Ice Cream",
                            responseCode = "-1"
                    )
            }
    )
        
    @GetMapping("/{iceCreamId}")
    public IceCreamDTO getIceCreamById(@PathVariable long iceCreamId) {
        Optional<IceCream> iceCream = iceCreamService.findIceCreamById(iceCreamId);

        boolean iceCreamExist = iceCream.isPresent();
        if (!iceCreamExist) {
            return new IceCreamDTO(-1, "IceCream does not exist", null);
        }
        return new IceCreamDTO(1, "IceCream exists", iceCream.get());
    }

	@GetMapping("/all")
    public IceCreamsDTO getMethodName() {
        List<IceCream> iceCreams = iceCreamService.findAllIceCreams();

        if (iceCreams.isEmpty())
            return new IceCreamsDTO(-1, "There are no IceCreams", iceCreams);
		return new IceCreamsDTO(1, "Ice Cream list", iceCreams);
		}

    @Operation(
            description = "Post endpoint to add a new Ice Cream",
            summary = "Use the Post endpoint to add a new Ice Cream",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "1"
                    ),
                    @ApiResponse(
                            description = "Ice Cream already exists",
                            responseCode = "-1"
                    )
            }
    )
    @PostMapping("/add")
    public IceCreamDTO addIceCream(@RequestBody IceCreamRequestDTO iceCreamRequestDTO) {
        boolean doesIceCreamExist = iceCreamService.doesIceCreamExist(iceCreamRequestDTO.getId());
        if (doesIceCreamExist)
            return new IceCreamDTO(-1, "IceCream already exists", null);

        try {
            IceCream iceCream = iceCreamService.toIceCream(iceCreamRequestDTO);
            System.out.println(iceCreamRequestDTO);
            System.out.println(iceCream);
            iceCreamService.saveIceCream(iceCream);
            return new IceCreamDTO(1, "IceCream added", iceCream);
        } catch (ResourceNotFoundException e) {
            return new IceCreamDTO(-1, e.getMessage(), null);
        }
    }

    @Operation(
            description = "Put endpoint to update an existing Ice Cream",
            summary = "Use the Put endpoint to update an existing Ice Cream",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "1"
                    ),
                    @ApiResponse(
                            description = "Ice Cream does not exist",
                            responseCode = "-1"
                    )
            }
    )
    @PutMapping("/update")
    public IceCreamDTO updateIceCream(@RequestBody IceCreamRequestDTO iceCreamRequestDTO) throws ResourceNotFoundException {
        Optional<IceCream> oldIceCream = iceCreamService.findIceCreamById(iceCreamRequestDTO.getId());
        boolean iceCreamExist = oldIceCream.isPresent();

        if (!iceCreamExist)
            return new IceCreamDTO(-1, "IceCream does not exist!", null);

        IceCreamRequestDTO oldIceCreamRequestDTO = iceCreamService.toIceCreamRequestDTO(oldIceCream.get());
        IceCreamRequestDTO mergedIceCreamRequestDTO = iceCreamService.mergeIceCreamRequestDTO(iceCreamRequestDTO, oldIceCreamRequestDTO);

        try {
            IceCream newIceCream = iceCreamService.toIceCream(mergedIceCreamRequestDTO);
            iceCreamService.saveIceCream(newIceCream);
            return new IceCreamDTO(1, "IceCream updated", newIceCream);
        } catch (ResourceNotFoundException e) {
            return new IceCreamDTO(-1, e.getMessage(), null);
        }
    }

    @Operation(
            description = "Delete endpoint to remove an Ice Cream",
            summary = "Use the Delete endpoint to remove an Ice Cream",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "1"
                    ),
                    @ApiResponse(
                            description = "Ice Cream does not exist",
                            responseCode = "-1"
                    )
            }
    )
    @Transactional
    @DeleteMapping("/delete")
    public IceCreamDTO delete(@RequestBody long iceCreamId) {
        boolean iceCreamExist = iceCreamService.doesIceCreamExist(iceCreamId);
        if (!iceCreamExist)
            return new IceCreamDTO(-1, "IceCream does not exist", null);

        iceCreamService.deleteIceCream(iceCreamId);
        return new IceCreamDTO(1, "IceCream deleted", null);
    }
}
