import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from '../service/message.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.scss']
})
export class MessageComponent implements OnInit {

  public message: any = { 'title': '', 'text': '' };

  constructor(private route: ActivatedRoute, private messageService: MessageService) {
    this.route.params.subscribe(params => {
      console.log(params);
      this.messageService.readMessage(params).subscribe(
        (data) => { this.message = data; },
        (err) => console.log('Could not send Message update read to server, reason: ', err),
        () => console.log('Sent Message update read to server.'),
      );
    });
  }

  ngOnInit() {
  }

}
