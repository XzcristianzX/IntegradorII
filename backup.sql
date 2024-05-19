PGDMP      &                |            animal    16.2    16.2 M    B           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            C           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            D           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            E           1262    17095    animal    DATABASE     |   CREATE DATABASE animal WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Colombia.1252';
    DROP DATABASE animal;
                postgres    false            �            1259    17189    activity    TABLE     }   CREATE TABLE public.activity (
    id_activity integer NOT NULL,
    duration_time character varying,
    implements text
);
    DROP TABLE public.activity;
       public         heap    postgres    false            �            1259    17188    activity_id_activity_seq    SEQUENCE     �   CREATE SEQUENCE public.activity_id_activity_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.activity_id_activity_seq;
       public          postgres    false    224            F           0    0    activity_id_activity_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.activity_id_activity_seq OWNED BY public.activity.id_activity;
          public          postgres    false    223            �            1259    17260    adoption_post    TABLE     �   CREATE TABLE public.adoption_post (
    id_adoption_post integer NOT NULL,
    title character varying,
    body text,
    id_user integer,
    status boolean,
    created_at character varying,
    img character varying
);
 !   DROP TABLE public.adoption_post;
       public         heap    postgres    false            �            1259    17259 "   adoption_post_id_adoption_post_seq    SEQUENCE     �   CREATE SEQUENCE public.adoption_post_id_adoption_post_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.adoption_post_id_adoption_post_seq;
       public          postgres    false    232            G           0    0 "   adoption_post_id_adoption_post_seq    SEQUENCE OWNED BY     i   ALTER SEQUENCE public.adoption_post_id_adoption_post_seq OWNED BY public.adoption_post.id_adoption_post;
          public          postgres    false    231            �            1259    17226    animal    TABLE     �  CREATE TABLE public.animal (
    id_animal integer NOT NULL,
    id_type integer NOT NULL,
    id_race integer NOT NULL,
    id_vaccine integer,
    id_location integer,
    id_owner integer NOT NULL,
    name character varying NOT NULL,
    weight character varying NOT NULL,
    size character varying NOT NULL,
    gender character(1) NOT NULL,
    img character varying,
    birthdate date NOT NULL,
    status boolean
);
    DROP TABLE public.animal;
       public         heap    postgres    false            �            1259    17225    animal_id_animal_seq    SEQUENCE     �   CREATE SEQUENCE public.animal_id_animal_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.animal_id_animal_seq;
       public          postgres    false    230            H           0    0    animal_id_animal_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.animal_id_animal_seq OWNED BY public.animal.id_animal;
          public          postgres    false    229            �            1259    17198    careful    TABLE        CREATE TABLE public.careful (
    id_careful integer NOT NULL,
    id_activity integer,
    feeding text,
    bathroom text
);
    DROP TABLE public.careful;
       public         heap    postgres    false            �            1259    17197    careful_id_careful_seq    SEQUENCE     �   CREATE SEQUENCE public.careful_id_careful_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.careful_id_careful_seq;
       public          postgres    false    226            I           0    0    careful_id_careful_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.careful_id_careful_seq OWNED BY public.careful.id_careful;
          public          postgres    false    225            �            1259    17171    location    TABLE     �   CREATE TABLE public.location (
    id_location integer NOT NULL,
    coordenate character varying,
    route character varying
);
    DROP TABLE public.location;
       public         heap    postgres    false            �            1259    17170    location_id_location_seq    SEQUENCE     �   CREATE SEQUENCE public.location_id_location_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.location_id_location_seq;
       public          postgres    false    220            J           0    0    location_id_location_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.location_id_location_seq OWNED BY public.location.id_location;
          public          postgres    false    219            �            1259    17212    race    TABLE     �   CREATE TABLE public.race (
    id_race integer NOT NULL,
    id_careful integer NOT NULL,
    name character varying(20) NOT NULL,
    description text
);
    DROP TABLE public.race;
       public         heap    postgres    false            �            1259    17211    race_id_race_seq    SEQUENCE     �   CREATE SEQUENCE public.race_id_race_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.race_id_race_seq;
       public          postgres    false    228            K           0    0    race_id_race_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.race_id_race_seq OWNED BY public.race.id_race;
          public          postgres    false    227            �            1259    17164    type_animal    TABLE     k   CREATE TABLE public.type_animal (
    id_type integer NOT NULL,
    name character varying(20) NOT NULL
);
    DROP TABLE public.type_animal;
       public         heap    postgres    false            �            1259    17163    type_animal_id_type_seq    SEQUENCE     �   CREATE SEQUENCE public.type_animal_id_type_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.type_animal_id_type_seq;
       public          postgres    false    218            L           0    0    type_animal_id_type_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.type_animal_id_type_seq OWNED BY public.type_animal.id_type;
          public          postgres    false    217            �            1259    17151    user    TABLE     �  CREATE TABLE public."user" (
    id_user integer NOT NULL,
    user_name character varying(20) NOT NULL,
    name character varying(50) NOT NULL,
    birthdate character varying NOT NULL,
    mail character varying(50) NOT NULL,
    password character varying(4) NOT NULL,
    img_profile character varying,
    gender character(1) NOT NULL,
    status boolean,
    phone character varying(10),
    codigo_login character varying(4)
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    17150    user_id_user_seq    SEQUENCE     �   CREATE SEQUENCE public.user_id_user_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.user_id_user_seq;
       public          postgres    false    216            M           0    0    user_id_user_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.user_id_user_seq OWNED BY public."user".id_user;
          public          postgres    false    215            �            1259    17180    vaccine    TABLE     �   CREATE TABLE public.vaccine (
    id_vaccine integer NOT NULL,
    name character varying(20),
    created_at character varying,
    description text,
    status boolean
);
    DROP TABLE public.vaccine;
       public         heap    postgres    false            �            1259    17179    vaccine_id_vaccine_seq    SEQUENCE     �   CREATE SEQUENCE public.vaccine_id_vaccine_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.vaccine_id_vaccine_seq;
       public          postgres    false    222            N           0    0    vaccine_id_vaccine_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.vaccine_id_vaccine_seq OWNED BY public.vaccine.id_vaccine;
          public          postgres    false    221            |           2604    17192    activity id_activity    DEFAULT     |   ALTER TABLE ONLY public.activity ALTER COLUMN id_activity SET DEFAULT nextval('public.activity_id_activity_seq'::regclass);
 C   ALTER TABLE public.activity ALTER COLUMN id_activity DROP DEFAULT;
       public          postgres    false    223    224    224            �           2604    17263    adoption_post id_adoption_post    DEFAULT     �   ALTER TABLE ONLY public.adoption_post ALTER COLUMN id_adoption_post SET DEFAULT nextval('public.adoption_post_id_adoption_post_seq'::regclass);
 M   ALTER TABLE public.adoption_post ALTER COLUMN id_adoption_post DROP DEFAULT;
       public          postgres    false    232    231    232                       2604    17229    animal id_animal    DEFAULT     t   ALTER TABLE ONLY public.animal ALTER COLUMN id_animal SET DEFAULT nextval('public.animal_id_animal_seq'::regclass);
 ?   ALTER TABLE public.animal ALTER COLUMN id_animal DROP DEFAULT;
       public          postgres    false    230    229    230            }           2604    17201    careful id_careful    DEFAULT     x   ALTER TABLE ONLY public.careful ALTER COLUMN id_careful SET DEFAULT nextval('public.careful_id_careful_seq'::regclass);
 A   ALTER TABLE public.careful ALTER COLUMN id_careful DROP DEFAULT;
       public          postgres    false    225    226    226            z           2604    17174    location id_location    DEFAULT     |   ALTER TABLE ONLY public.location ALTER COLUMN id_location SET DEFAULT nextval('public.location_id_location_seq'::regclass);
 C   ALTER TABLE public.location ALTER COLUMN id_location DROP DEFAULT;
       public          postgres    false    219    220    220            ~           2604    17215    race id_race    DEFAULT     l   ALTER TABLE ONLY public.race ALTER COLUMN id_race SET DEFAULT nextval('public.race_id_race_seq'::regclass);
 ;   ALTER TABLE public.race ALTER COLUMN id_race DROP DEFAULT;
       public          postgres    false    227    228    228            y           2604    17167    type_animal id_type    DEFAULT     z   ALTER TABLE ONLY public.type_animal ALTER COLUMN id_type SET DEFAULT nextval('public.type_animal_id_type_seq'::regclass);
 B   ALTER TABLE public.type_animal ALTER COLUMN id_type DROP DEFAULT;
       public          postgres    false    217    218    218            x           2604    17154    user id_user    DEFAULT     n   ALTER TABLE ONLY public."user" ALTER COLUMN id_user SET DEFAULT nextval('public.user_id_user_seq'::regclass);
 =   ALTER TABLE public."user" ALTER COLUMN id_user DROP DEFAULT;
       public          postgres    false    215    216    216            {           2604    17183    vaccine id_vaccine    DEFAULT     x   ALTER TABLE ONLY public.vaccine ALTER COLUMN id_vaccine SET DEFAULT nextval('public.vaccine_id_vaccine_seq'::regclass);
 A   ALTER TABLE public.vaccine ALTER COLUMN id_vaccine DROP DEFAULT;
       public          postgres    false    221    222    222            7          0    17189    activity 
   TABLE DATA           J   COPY public.activity (id_activity, duration_time, implements) FROM stdin;
    public          postgres    false    224   �Z       ?          0    17260    adoption_post 
   TABLE DATA           h   COPY public.adoption_post (id_adoption_post, title, body, id_user, status, created_at, img) FROM stdin;
    public          postgres    false    232   �Z       =          0    17226    animal 
   TABLE DATA           �   COPY public.animal (id_animal, id_type, id_race, id_vaccine, id_location, id_owner, name, weight, size, gender, img, birthdate, status) FROM stdin;
    public          postgres    false    230   �[       9          0    17198    careful 
   TABLE DATA           M   COPY public.careful (id_careful, id_activity, feeding, bathroom) FROM stdin;
    public          postgres    false    226   \       3          0    17171    location 
   TABLE DATA           B   COPY public.location (id_location, coordenate, route) FROM stdin;
    public          postgres    false    220   z\       ;          0    17212    race 
   TABLE DATA           F   COPY public.race (id_race, id_careful, name, description) FROM stdin;
    public          postgres    false    228   �\       1          0    17164    type_animal 
   TABLE DATA           4   COPY public.type_animal (id_type, name) FROM stdin;
    public          postgres    false    218   v]       /          0    17151    user 
   TABLE DATA           �   COPY public."user" (id_user, user_name, name, birthdate, mail, password, img_profile, gender, status, phone, codigo_login) FROM stdin;
    public          postgres    false    216   �]       5          0    17180    vaccine 
   TABLE DATA           T   COPY public.vaccine (id_vaccine, name, created_at, description, status) FROM stdin;
    public          postgres    false    222   }^       O           0    0    activity_id_activity_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.activity_id_activity_seq', 2, true);
          public          postgres    false    223            P           0    0 "   adoption_post_id_adoption_post_seq    SEQUENCE SET     P   SELECT pg_catalog.setval('public.adoption_post_id_adoption_post_seq', 2, true);
          public          postgres    false    231            Q           0    0    animal_id_animal_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.animal_id_animal_seq', 2, true);
          public          postgres    false    229            R           0    0    careful_id_careful_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.careful_id_careful_seq', 2, true);
          public          postgres    false    225            S           0    0    location_id_location_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.location_id_location_seq', 2, true);
          public          postgres    false    219            T           0    0    race_id_race_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.race_id_race_seq', 2, true);
          public          postgres    false    227            U           0    0    type_animal_id_type_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.type_animal_id_type_seq', 3, true);
          public          postgres    false    217            V           0    0    user_id_user_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.user_id_user_seq', 3, true);
          public          postgres    false    215            W           0    0    vaccine_id_vaccine_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.vaccine_id_vaccine_seq', 2, true);
          public          postgres    false    221            �           2606    17196    activity activity_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.activity
    ADD CONSTRAINT activity_pkey PRIMARY KEY (id_activity);
 @   ALTER TABLE ONLY public.activity DROP CONSTRAINT activity_pkey;
       public            postgres    false    224            �           2606    17267     adoption_post adoption_post_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.adoption_post
    ADD CONSTRAINT adoption_post_pkey PRIMARY KEY (id_adoption_post);
 J   ALTER TABLE ONLY public.adoption_post DROP CONSTRAINT adoption_post_pkey;
       public            postgres    false    232            �           2606    17233    animal animal_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (id_animal);
 <   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_pkey;
       public            postgres    false    230            �           2606    17205    careful careful_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.careful
    ADD CONSTRAINT careful_pkey PRIMARY KEY (id_careful);
 >   ALTER TABLE ONLY public.careful DROP CONSTRAINT careful_pkey;
       public            postgres    false    226            �           2606    17178    location location_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.location
    ADD CONSTRAINT location_pkey PRIMARY KEY (id_location);
 @   ALTER TABLE ONLY public.location DROP CONSTRAINT location_pkey;
       public            postgres    false    220            �           2606    17219    race race_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.race
    ADD CONSTRAINT race_pkey PRIMARY KEY (id_race);
 8   ALTER TABLE ONLY public.race DROP CONSTRAINT race_pkey;
       public            postgres    false    228            �           2606    17169    type_animal type_animal_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.type_animal
    ADD CONSTRAINT type_animal_pkey PRIMARY KEY (id_type);
 F   ALTER TABLE ONLY public.type_animal DROP CONSTRAINT type_animal_pkey;
       public            postgres    false    218            �           2606    17162    user user_mail_key 
   CONSTRAINT     O   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_mail_key UNIQUE (mail);
 >   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_mail_key;
       public            postgres    false    216            �           2606    17158    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id_user);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    216            �           2606    17160    user user_user_name_key 
   CONSTRAINT     Y   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_user_name_key UNIQUE (user_name);
 C   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_user_name_key;
       public            postgres    false    216            �           2606    17187    vaccine vaccine_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT vaccine_pkey PRIMARY KEY (id_vaccine);
 >   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT vaccine_pkey;
       public            postgres    false    222            �           2606    17268 (   adoption_post adoption_post_id_user_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.adoption_post
    ADD CONSTRAINT adoption_post_id_user_fkey FOREIGN KEY (id_user) REFERENCES public."user"(id_user);
 R   ALTER TABLE ONLY public.adoption_post DROP CONSTRAINT adoption_post_id_user_fkey;
       public          postgres    false    4740    216    232            �           2606    17249    animal animal_id_location_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_id_location_fkey FOREIGN KEY (id_location) REFERENCES public.location(id_location);
 H   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_id_location_fkey;
       public          postgres    false    230    220    4746            �           2606    17254    animal animal_id_owner_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_id_owner_fkey FOREIGN KEY (id_owner) REFERENCES public."user"(id_user);
 E   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_id_owner_fkey;
       public          postgres    false    230    216    4740            �           2606    17239    animal animal_id_race_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_id_race_fkey FOREIGN KEY (id_race) REFERENCES public.race(id_race);
 D   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_id_race_fkey;
       public          postgres    false    228    230    4754            �           2606    17234    animal animal_id_type_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_id_type_fkey FOREIGN KEY (id_type) REFERENCES public.type_animal(id_type);
 D   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_id_type_fkey;
       public          postgres    false    230    4744    218            �           2606    17244    animal animal_id_vaccine_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_id_vaccine_fkey FOREIGN KEY (id_vaccine) REFERENCES public.vaccine(id_vaccine);
 G   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_id_vaccine_fkey;
       public          postgres    false    222    230    4748            �           2606    17206     careful careful_id_activity_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.careful
    ADD CONSTRAINT careful_id_activity_fkey FOREIGN KEY (id_activity) REFERENCES public.activity(id_activity);
 J   ALTER TABLE ONLY public.careful DROP CONSTRAINT careful_id_activity_fkey;
       public          postgres    false    4750    226    224            �           2606    17220    race race_id_careful_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.race
    ADD CONSTRAINT race_id_careful_fkey FOREIGN KEY (id_careful) REFERENCES public.careful(id_careful);
 C   ALTER TABLE ONLY public.race DROP CONSTRAINT race_id_careful_fkey;
       public          postgres    false    226    228    4752            7   F   x�3�46P���+-�/��*M/M-I-�QH�/*JM�2�4T��/J�t�L�L�I-I�Q(H��/I����� o�6      ?   �   x�]α�0��<�-+6��б�$�9�����B��ŰAB
ͯ��w
��Z^^;�G�<�@3cr8�<�Ȗ���o�&K�8l��R9�ͫ�$(��k���m����}��m�B���%� ��H�R���g�b���	����$A��]U+��=K!�S�N�      =   `   x�3�4�C�̔|Nc��tN��ļ�TN_�4��^VA:����������)g	�'�f�fr��4���ޘ��ƙ�j32�5��54 j����� ��%      9   h   x�]̽	�0��:7�M �t���@~$g,��B!�im���Xc�C��Å�d����ʾ(�D�}�`f��p����[����A���&. ��?~\�e ���-�      3   Y   x�3�4��31623��Qе��34662��t�,MILQHIU�=��"39�ˈ��@���������D�����܄ӯ4�,Q!2�(�+F��� -��      ;   �   x�%���0�k{� !%sP����~D'9�u6Ha�̑ňD��np7̆XL&vS�in��U̉�	�ۢ�H$�0��4���э�X����HYЋ��K��羽dfJ<�&v|C��������?�����5      1   "   x�3�H-*��2�tO,��2�t,K����� \
'      /   �   x�U�K
�0���^ ���ɮ�Bi��&�X��Jo�EW=B.�4�f��e�[.$�޽��{w�\kF���4u[������	��ˋ��e{����9�,W\��
�p2���h�u�R��㘘��\M��n���RL$�72!�G�gJ��������)�,$f�f\L���Χ���a�Ô\bB��LN�      5   V   x�3�KL.�KTJL�L�4202�50"�xr~^IQ�BN�BXE	�L* ��,�,����������2��ļ̼|�1z\\\ 5)&Q     