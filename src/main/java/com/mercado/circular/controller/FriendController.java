package com.mercado.circular.controller;

import com.mercado.circular.MDto.FriendDTO;
import com.mercado.circular.MDto.FriendRequestDTO;
import com.mercado.circular.security.entity.Usuario;
import com.mercado.circular.security.jwt.JwtProvider;
import com.mercado.circular.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Transactional
@RequestMapping("/api/v1/friends")
@CrossOrigin(origins = "http://localhost:4200")
public class FriendController {
    private final UsuarioService usuarioService;
    private final JwtProvider jwtProvider;

    @Autowired
    public FriendController(UsuarioService usuarioService, JwtProvider jwtProvider) {
        this.usuarioService = usuarioService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/send-request/{receiverId}")
    public ResponseEntity<String> sendFriendRequest(@PathVariable int receiverId, Authentication authentication) {
        String senderNombreUsuario = authentication.getName();
        Optional<Usuario> senderOptional = usuarioService.getByNombreUsuario(senderNombreUsuario);
        Optional<Usuario> receiverOptional = usuarioService.getById(receiverId);

        if (senderOptional.isEmpty() || receiverOptional.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        Usuario sender = senderOptional.get();
        Usuario receiver = receiverOptional.get();

        if (sender.equals(receiver)) {
            return new ResponseEntity<>("You cannot send a friend request to yourself", HttpStatus.BAD_REQUEST);
        }

        if (receiver.getFriends().contains(sender) || sender.getPendingFriendRequests().contains(receiver)) {
            return new ResponseEntity<>("Friend request already sent", HttpStatus.BAD_REQUEST);
        }

        receiver.getPendingFriendRequests().add(sender);
        usuarioService.save(receiver);

        return new ResponseEntity<>("Friend request sent successfully", HttpStatus.OK);
    }

    @PostMapping("/accept-request/{senderId}")
    public ResponseEntity<String> acceptFriendRequest(@PathVariable int senderId, Authentication authentication) {
        String receiverNombreUsuario = authentication.getName();
        Optional<Usuario> receiverOptional = usuarioService.getByNombreUsuario(receiverNombreUsuario);
        Optional<Usuario> senderOptional = usuarioService.getById(senderId);

        if (receiverOptional.isEmpty() || senderOptional.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        Usuario receiver = receiverOptional.get();
        Usuario sender = senderOptional.get();

        if (!receiver.getPendingFriendRequests().contains(sender)) {
            return new ResponseEntity<>("No friend request found", HttpStatus.BAD_REQUEST);
        }

        receiver.getPendingFriendRequests().remove(sender);
        receiver.addFriend(sender);

        usuarioService.save(receiver);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/received-requests")
    public ResponseEntity<List<FriendRequestDTO>> getReceivedFriendRequests(Authentication authentication) {
        String nombreUsuario = authentication.getName();
        Optional<Usuario> receiverOptional = usuarioService.getByNombreUsuario(nombreUsuario);

        if (receiverOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Usuario receiver = receiverOptional.get();
        Set<Usuario> receivedRequests = receiver.getPendingFriendRequests();

        // Convert the Usuario objects to FriendRequestDTO objects
        List<FriendRequestDTO> friendRequestsDTO = receivedRequests.stream()
                .map(user -> new FriendRequestDTO((long) user.getId(), user.getNombreUsuario()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(friendRequestsDTO, HttpStatus.OK);
    }



    @GetMapping("/amigos")
    public ResponseEntity<List<FriendDTO>> getMyFriends(Authentication authentication) {
        String nombreUsuario = authentication.getName();
        Optional<Usuario> userOptional = usuarioService.getByNombreUsuario(nombreUsuario);

        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Usuario user = userOptional.get();
        Set<Usuario> friends = user.getFriends();

        // Convert the Usuario objects to FriendDTO objects containing only id and nombreUsuario
        List<FriendDTO> friendsDTO = friends.stream()
                .map(friend -> new FriendDTO((long) friend.getId(), friend.getNombreUsuario()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(friendsDTO, HttpStatus.OK);
    }

    // Additional methods can be added here

}

