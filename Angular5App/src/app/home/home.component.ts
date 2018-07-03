import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { SwPush } from '@angular/service-worker';
import { NotificationService } from '../service/notification.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  title = 'Notification Application';
  sub: PushSubscription;

  readonly VAPID_PUBLIC_KEY = 'BPkd7Yapz24ESGNg4t02KpXcauE_yRUrV2OctDuiuuc4ha0J95OOZvTwD2p_S-FNbDdG8gDdcSZf8ZlmyhSnsEc';

  constructor(
    private swPush: SwPush,
    private notificationService: NotificationService) {
  }

  ngOnInit() {
   this.subscribeToNotifications();
  }

  subscribeToNotifications() {
    console.log('Inside Susbscribe to Notifications');
    this.swPush.requestSubscription({
      serverPublicKey: this.VAPID_PUBLIC_KEY
    })
      .then(sub => {
        this.sub = sub;
        console.log('Notification Subscription: ', sub);
        this.notificationService.addPushSubscriber(sub).subscribe(
          () => console.log('Sent push subscription object to server.'),
          err => console.log('Could not send subscription object to server, reason: ', err)
        );
      })
      .catch(err => console.error('Could not subscribe to notifications', err));

  }
}

